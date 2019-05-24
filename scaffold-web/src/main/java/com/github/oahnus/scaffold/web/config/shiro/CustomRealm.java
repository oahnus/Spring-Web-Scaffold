package com.github.oahnus.scaffold.web.config.shiro;

import com.github.oahnus.scaffold.domain.entity.SysPermission;
import com.github.oahnus.scaffold.domain.entity.SysRole;
import com.github.oahnus.scaffold.domain.entity.UserAuth;
import com.github.oahnus.scaffold.web.service.AuthService;
import com.github.oahnus.scaffold.web.service.impl.PermissionService;
import com.github.oahnus.scaffold.web.service.impl.RoleService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by oahnus on 2019/4/24
 * 15:38.
 */
// 此 Component 必须起名为 authorizer 否则抛异常：
// Parameter 0 of method authorizationAttributeSourceAdvisor in org.apache.shiro.spring.boot.autoconfigure.ShiroAnnotationProcessorAutoConfiguration required a bean named 'authorizer' that could not be found.
//@Component("authorizer")
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    @Lazy
    RoleService roleService;
    @Autowired
    @Lazy
    PermissionService permissionService;
    @Autowired
    @Lazy
    AuthService authService;
    @Autowired
    @Lazy
    SessionDAO sessionDAO;

    {
        //设置用于匹配密码的CredentialsMatcher
//        HashedCredentialsMatcher hashMatcher = new HashedCredentialsMatcher();
//        hashMatcher.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
//        hashMatcher.setStoredCredentialsHexEncoded(false);
//        hashMatcher.setHashIterations(1024);
//        this.setCredentialsMatcher(hashMatcher);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        if (principalCollection == null) {
            throw new AuthenticationException("principalCollection is null");
        }
        UserAuth userAuth = (UserAuth) getAvailablePrincipal(principalCollection);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        System.out.println("获取角色信息："+ userAuth.getRoles());
        System.out.println("获取权限信息："+ userAuth.getPerms());
        info.setRoles(userAuth.getRoles());
        info.setStringPermissions(userAuth.getPerms());
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String username = upToken.getUsername();

        // Null username is invalid
        if (username == null) {
            throw new AccountException("Null username are not allowed by this realm.");
        }

        UserAuth auth = authService.findAuthByUsername(username);

        if (auth == null) {
            throw new UnknownAccountException("No account found for admin [" + username + "]");
        }

        if (auth.getIsDelete()) {
            throw new AccountException("user is deleted");
        }

        // 获取所有活动session
//        Collection<Session> activeSessions = sessionDAO.getActiveSessions();
//        System.out.println(activeSessions);

        //查询用户的角色和权限存到SimpleAuthenticationInfo中，这样在其它地方
        //SecurityUtils.getSubject().getPrincipal()就能拿出用户的所有信息，包括角色和权限
        List<SysRole> roles = roleService.getRolesByAuthId(auth.getId());
        List<Long> roleIds = roles.stream().map(SysRole::getId).collect(Collectors.toList());
        List<SysPermission> perms = permissionService.getPermByRoleIds(auth.getId());

        Set<String> stringRoles = roles.stream().map(SysRole::getName).collect(Collectors.toSet());
        auth.getRoles().addAll(stringRoles);
        Set<String> stringPerms = perms.stream().map(SysPermission::getName).collect(Collectors.toSet());
        auth.getPerms().addAll(stringPerms);

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(auth, auth.getPassword(), getName());
        if (auth.getSalt() != null) {
            info.setCredentialsSalt(ByteSource.Util.bytes(auth.getSalt()));
        }

        return info;
    }
}
