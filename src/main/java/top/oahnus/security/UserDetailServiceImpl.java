package top.oahnus.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.oahnus.common.exception.AuthException;
import top.oahnus.domain.factory.AuthFactory;
import top.oahnus.domain.primary.UserAuth;
import top.oahnus.mapper.primary.UserAuthMapper;
import top.oahnus.service.AuthService;

/**
 * Created by oahnus on 2018/8/20
 * 10:34.
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserAuthMapper authMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserAuth auth = authMapper.findFirstByUsername(s);
        if (auth == null) {
            throw new AuthException("");
        }
        return auth;
    }
}
