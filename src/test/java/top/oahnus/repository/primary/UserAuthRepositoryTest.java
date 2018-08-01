package top.oahnus.repository.primary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.oahnus.common.utils.MD5Helper;
import top.oahnus.common.utils.PasswordHash;
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
    public void checkAuth() throws Exception {
//        UserAuth auth = userAuthRepository.findFirstByUsernameAndPassword("root", MD5Helper.getMd5("123456"));
//        System.out.println(auth);
//        UserAuth auth = userAuthRepository.findByUsername("root");
//        if (auth == null) {
//            System.out.println("user auth null");
//            return;
//        }
//        String res = PasswordHash.createHash("123456");
//        System.out.println(res);
//
//        String salt = res.split(":")[1];
//        String hash = res.split(":")[2];
//        auth.setPwdHash(hash);
//        auth.setSalt(salt);
//        userAuthRepository.save(auth);
    }
}