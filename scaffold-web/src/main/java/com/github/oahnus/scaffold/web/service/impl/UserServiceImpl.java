package com.github.oahnus.scaffold.web.service.impl;

import com.github.oahnus.scaffold.common.service.BaseService;
import com.github.oahnus.scaffold.domain.entity.User;
import com.github.oahnus.scaffold.domain.mapper.UserMapper;
import com.github.oahnus.scaffold.web.config.db.TargetDataSource;
import com.github.oahnus.scaffold.web.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by oahnus on 2019/10/22
 * 22:05.
 */
@Service
public class UserServiceImpl extends BaseService<UserMapper, User, Long> implements UserService {

    @Override
    public List<User> listUserInFirstDB() {
        return mapper.selectAll();
    }

    @Override
    @TargetDataSource(name = "second")
    public List<User> listUserInSecondDB() {
        return mapper.selectAll();
    }
}
