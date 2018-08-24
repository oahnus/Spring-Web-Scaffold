package top.oahnus.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by oahnus on 2018/8/20
 * 13:44.
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    @Override
    public Boolean hasAccess() {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .contains(new SimpleGrantedAuthority("ADMIN"));
    }
}
