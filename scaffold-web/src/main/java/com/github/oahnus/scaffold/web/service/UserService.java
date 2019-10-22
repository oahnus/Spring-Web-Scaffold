package com.github.oahnus.scaffold.web.service;


import com.github.oahnus.scaffold.domain.entity.User;

import java.util.List;

/**
 * Created by oahnus on 2019/4/15
 * 13:27.
 */
public interface UserService {
    List<User> listUserInFirstDB();
    List<User> listUserInSecondDB();
}
