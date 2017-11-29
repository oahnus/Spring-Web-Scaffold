package top.oahnus.service.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.oahnus.common.config.RedisDao;

/**
 * Created by oahnus on 2017/11/29
 * 12:04.
 */
@Service
public class RedisSessionService implements SessionService {

    @Autowired
    private RedisDao redisDao;

    private final String TOKEN_KEY_BASE = "TOKEN:";

    @Override
    public Long saveToken(Long userId, String token) {
        String key = KEY_BASE + TOKEN_KEY_BASE + userId;
        redisDao.putBean(key, token, String.class, EXPIRE * 60 * 60); // timeout 7 days
        return userId;
    }

    @Override
    public Long getUserId(String token) {
        return null;
    }

    public String getToken(Long userId, String token) {
        String key = KEY_BASE + TOKEN_KEY_BASE + userId;
        return redisDao.getBean(key, String.class);
    }
}
