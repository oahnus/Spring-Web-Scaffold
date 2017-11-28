package top.oahnus.repository.primary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.oahnus.common.utils.MD5Helper;
import top.oahnus.domain.primary.UserAuth;

import static org.junit.Assert.*;

/**
 * Created by oahnus on 2017/11/28
 * 10:06.
 */
@SpringBootTest
@EnableAutoConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class UserAuthRepositoryTest {
    @Autowired
    private UserAuthRepository userAuthRepository;

    @Test
    public void findFirstByUsernameAndPassword() throws Exception {
        UserAuth auth = userAuthRepository.findFirstByUsernameAndPassword("root", MD5Helper.getMd5("123456"));
        System.out.println(auth);
    }

}