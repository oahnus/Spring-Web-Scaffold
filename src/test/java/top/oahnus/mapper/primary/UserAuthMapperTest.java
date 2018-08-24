package top.oahnus.mapper.primary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.oahnus.domain.primary.User;
import top.oahnus.mybatis.condition.Condition;

import static org.junit.Assert.*;

/**
 * Created by oahnus on 2018/8/8
 * 18:02.
 */
@SpringBootTest
@EnableAutoConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class UserAuthMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void findFirstByUsername() {
//        UserAuth userAuth = userAuthMapper.findFirstByUsername("root");
//        System.out.println(userAuth);

        System.out.println(userMapper.selectOneByCondition(Condition.create(User.class).eq("username", "root")));
    }
}