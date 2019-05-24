package com.github.oahnus.scaffold.web.service;


import com.github.oahnus.scaffold.domain.entity.UserAuth;

/**
 * Created by oahnus on 2019/4/7
 * 22:15.
 */
public interface AuthService {
    UserAuth login(String username, String password);

    UserAuth findAuthByUsername(String username);

//    UserAuth register(String username, String password);
//    UserAuth register(String openId);
//    UserAuth resetPwd(Long userId, String oldPwd, String newPwd);
}
