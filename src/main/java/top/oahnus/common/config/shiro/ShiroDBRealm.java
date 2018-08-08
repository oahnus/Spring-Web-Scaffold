//package top.oahnus.common.config.shiro;
//
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import top.oahnus.domain.primary.UserAuth;
//import top.oahnus.mapper.primary.UserAuthMapper;
//import top.oahnus.service.AuthService;
//
//import javax.annotation.Resource;
//
///**
// * Created by oahnus on 2018/8/8
// * 14:55.
// */
//public class ShiroDBRealm extends AuthorizingRealm {
//
////    @Resource
////    private UserAuthMapper userAuthMapper;
//
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        UserAuth userAuth = (UserAuth) principalCollection.getPrimaryPrincipal();
//
//
//
//        return null;
//    }
//
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        return null;
//    }
//}
