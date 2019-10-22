package com.github.oahnus.scaffold.web.service.impl;

import com.github.oahnus.scaffold.domain.entity.User;
import com.github.oahnus.scaffold.web.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by oahnus on 2019/10/22
 * 22:06.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest {
    @Autowired
    UserService userService;

    @Test
    public void test() {
        List<User> users = userService.listUserInFirstDB();
        System.out.println(users);

        List<User> users2 = userService.listUserInSecondDB();
        System.out.println(users2);
    }
}