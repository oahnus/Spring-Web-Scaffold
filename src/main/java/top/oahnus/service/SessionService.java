package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.oahnus.common.config.RedisDao;
import top.oahnus.interfaces.SingleLoginMixin;

/**
 * Created by oahnus on 2017/10/3
 * 0:56.
 */
@Service
public class SessionService implements SingleLoginMixin {
    public static Integer expire = 7;

    @Autowired
    private RedisDao redisDao;

    @Override
    public RedisDao redis() {
        return redisDao;
    }
}
