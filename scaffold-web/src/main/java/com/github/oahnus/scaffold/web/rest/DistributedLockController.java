package com.github.oahnus.scaffold.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by oahnus on 2019/7/8
 * 15:18.
 */
@RestController
@RequestMapping("/dis")
public class DistributedLockController {
    private static Random random = new Random();
    @Autowired
    RedisTemplate<String, String> redisTemplate;
    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @GetMapping("/reduceRepo/{orderCode}")
    public String reduceRepo(@PathVariable("orderCode") String orderCode) {
        String key = genOrderKey(orderCode);
        System.out.println("Before Lock");
        Boolean isSuccess = redisTemplate.opsForValue().setIfAbsent(key, "lock");
        while (isSuccess == null || !isSuccess) {
            try {
                Thread.sleep(10, random.nextInt(10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isSuccess = redisTemplate.opsForValue().setIfAbsent(key, "lock");
        }
        redisTemplate.expire(key, 60, TimeUnit.SECONDS);
        System.out.println("Get Lock Success " + orderCode);

        // BIZ
        Date date = new Date();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        redisTemplate.delete(key);
        System.out.println("Release Lock");
        return date.toString();
    }

    private String genOrderKey(String orderCode) {
        return "scaffold:sync:order:" + orderCode;
    }
}
