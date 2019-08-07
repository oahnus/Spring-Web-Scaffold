package com.github.oahnus.scaffold.common.manager;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by oahnus on 2019/8/7
 * 16:19.
 */
@Component
public class DistributeLockViaRedis {
    private static Random RANDOM = new Random();
    private static long EXPIRE = 60; // 60s
    private static long TIMEOUT = 5000; // 5000 ms
    private static String LOCK = "LOCK";

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public boolean acquireLock(String key) {
        key = genKey(key);

        Boolean isSuccess = redisTemplate.opsForValue().setIfAbsent(key, LOCK);
        long start = System.currentTimeMillis();
        while (isSuccess == null || !isSuccess) {
            try {
                Thread.sleep(10, RANDOM.nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
            // TIMEOUT
            if (System.currentTimeMillis() - start > TIMEOUT) {
                return false;
            }
            isSuccess = redisTemplate.opsForValue().setIfAbsent(key, "lock");
        }
        redisTemplate.expire(key, EXPIRE, TimeUnit.SECONDS);
        return true;
    }

    public boolean releaseLock(String key) {
        key = genKey(key);
        Boolean delete = redisTemplate.delete(key);
        if (delete == null) {
            return false;
        }
        return delete;
    }

    private String genKey(String key) {
        return applicationName + ":lock:" + key;
    }
}
