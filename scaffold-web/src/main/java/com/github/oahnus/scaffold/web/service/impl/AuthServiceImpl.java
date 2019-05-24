package com.github.oahnus.scaffold.web.service.impl;

import com.github.oahnus.scaffold.common.exceptions.InvalidRequestParamException;
import com.github.oahnus.scaffold.common.query.QueryBuilder;
import com.github.oahnus.scaffold.common.service.BaseService;
import com.github.oahnus.scaffold.domain.entity.UserAuth;
import com.github.oahnus.scaffold.domain.mapper.UserAuthMapper;
import com.github.oahnus.scaffold.web.service.AuthService;
import org.springframework.stereotype.Service;

/**
 * Created by oahnus on 2019/4/7
 * 22:17.
 */
@Service("authService")
public class AuthServiceImpl extends BaseService<UserAuthMapper, UserAuth, Long> implements AuthService {

    @Override
    public UserAuth login(String username, String password) {
        QueryBuilder query = new QueryBuilder(UserAuth.class)
                .eq("username", username)
                .eq("password", password);
        UserAuth userAuth = mapper.selectOneByExample(query);

        if (userAuth == null) {
            throw new InvalidRequestParamException("用户名或密码错误");
        }
        return userAuth;
    }

    @Override
    public UserAuth findAuthByUsername(String username) {
        return this.selectOneByExample(new QueryBuilder(UserAuth.class).eq("username", username));
    }
}
