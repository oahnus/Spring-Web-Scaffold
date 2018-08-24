package top.oahnus.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.oahnus.common.exception.AuthException;
import top.oahnus.domain.primary.User;
import top.oahnus.mapper.primary.UserMapper;

/**
 * Created by oahnus on 2018/8/20
 * 10:34.
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.findFirstByUsername(s);
        if (user == null) {
            throw new AuthException("");
        }
        return user;
    }
}
