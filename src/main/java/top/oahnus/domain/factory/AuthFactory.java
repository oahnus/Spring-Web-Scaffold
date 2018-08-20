package top.oahnus.domain.factory;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.util.CollectionUtils;
import top.oahnus.domain.primary.UserAuth;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by oahnus on 2018/8/20
 * 10:41.
 */
public class AuthFactory {
    public static UserAuth create(UserAuth user) {
        Collection<? extends GrantedAuthority> authorities;
        try {
            authorities = user.getAuthorities();
        } catch (Exception e) {
            authorities = null;
        }
        return new UserAuth(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getSalt(),
                user.getUserId(),
                user.getLastPasswordReset(),
                Collections.emptyList(),
                authorities
        );
    }
}
