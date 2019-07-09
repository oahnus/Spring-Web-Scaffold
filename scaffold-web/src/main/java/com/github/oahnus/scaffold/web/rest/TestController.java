package com.github.oahnus.scaffold.web.rest;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.oahnus.scaffold.common.annotations.Download;
import com.github.oahnus.scaffold.common.utils.HttpUtil;
import com.github.oahnus.scaffold.web.rabbit.RabbitQueues;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.*;
import org.apache.shiro.subject.Subject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by oahnus on 2019/4/24
 * 16:20.
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/down1")
    @Download
    public File downloadOne() {
        String path = this.getClass().getClassLoader().getResource("file/test1.txt").getPath();
        System.out.println(path);
        File file = new File(path);
        System.out.println(file.exists());
        return file;
    }

    @GetMapping("/down2")
    @Download
    public List<File> downloadMore(HttpServletResponse response) {
        String path = this.getClass().getClassLoader().getResource("file/test1.txt").getPath();
        String path2 = this.getClass().getClassLoader().getResource("file/test2.txt").getPath();

        List<File> fileList = new ArrayList<>();
        fileList.add(new File(path));
        fileList.add(new File(path2));
        return fileList;
    }

    @GetMapping("/subject")
    @RequiresAuthentication
    public Object testSubject() {
        Subject subject = SecurityUtils.getSubject();
        return subject.getPrincipal();
    }

    @GetMapping("/hitokoto")
    public String testHitokoto() {
        String hitokoto = (String) redisTemplate.opsForValue().get("Scaffold:Hitokoto");
        if (StringUtils.isEmpty(hitokoto)) {
            String json = HttpUtil.hitokoto();
            HashMap map = JSON.parseObject(json, HashMap.class);
            if(map == null) {
                return "";
            }
            hitokoto = String.format("%s ---- 【%s】", map.get("hitokoto"), map.get("from"));
            redisTemplate.opsForValue().set("Scaffold:Hitokoto", hitokoto, 30, TimeUnit.SECONDS);
        }
        return hitokoto;
    }

    @GetMapping("/queue")
    public String testRabbitMQ() {
        Date date = new Date();
        rabbitTemplate.convertAndSend(RabbitQueues.TEST, date);
        return "success";
    }

    @GetMapping("/cache")
    @Cacheable(value = "Scaffold:CacheDate", keyGenerator = "keyGenerator")
    public String testCache() {
        System.out.println("not hit cache");
        return new Date().toString();
    }

    @GetMapping("/cache2")
    public String testCacheWithTemplate() {
        Date date = (Date) redisTemplate.opsForValue().get("Scaffold:CacheDate2:Date");
        if (date == null) {
            System.out.println("not hit cache2");
            date = new Date();
            redisTemplate.opsForValue().set("Scaffold:CacheDate2:Date", date);
        }
        return date.toString();
    }

    // 由于TestController类上没有加@RequiresAuthentication注解，
    // 不要求用户登录才能调用接口。所以hello()和a1()接口都是可以匿名访问的
    @GetMapping("/hello")
    public String hello() {
        return "hello spring boot";
    }

    // 游客可访问，这个有点坑，游客的意思是指：subject.getPrincipal()==null
    // 所以用户在未登录时subject.getPrincipal()==null，接口可访问
    // 而用户登录后subject.getPrincipal()！=null，接口不可访问
    @RequiresGuest
    @GetMapping("/guest")
    public String guest() {
        return "@RequiresGuest";
    }

    // 已登录用户才能访问，这个注解比@RequiresUser更严格
    // 如果用户未登录调用该接口，会抛出UnauthenticatedException
    @RequiresAuthentication
    @GetMapping("/authn")
    public String authn() {
        return "@RequiresAuthentication";
    }

    // 已登录用户或“记住我”的用户可以访问
    // 如果用户未登录或不是“记住我”的用户调用该接口，UnauthenticatedException
    @RequiresUser
    @GetMapping("/user")
    public String user() {
        return "@RequiresUser";
    }

    // 要求登录的用户具有mvn:build权限才能访问
    // 由于UserService模拟返回的用户信息中有该权限，所以这个接口可以访问
    // 如果没有登录，UnauthenticatedException
    @RequiresPermissions("mvn:install")
    @GetMapping("/mvnInstall")
    public String mvnInstall() {
        return "mvn:install";
    }

    // 要求登录的用户具有mvn:build权限才能访问
    // 由于UserService模拟返回的用户信息中【没有】该权限，所以这个接口【不可以】访问
    // 如果没有登录，UnauthenticatedException
    // 如果登录了，但是没有这个权限，会报错UnauthorizedException
    @RequiresPermissions("gradleBuild")
    @GetMapping("/gradleBuild")
    public String gradleBuild() {
        return "gradleBuild";
    }

    // 要求登录的用户具有js角色才能访问
    // 由于UserService模拟返回的用户信息中有该角色，所以这个接口可访问
    // 如果没有登录，UnauthenticatedException
    @RequiresRoles("js")
    @GetMapping("/js")
    public String js() {
        return "js programmer";
    }

    // 要求登录的用户具有js角色才能访问
    // 由于UserService模拟返回的用户信息中有该角色，所以这个接口可访问
    // 如果没有登录，UnauthenticatedException
    // 如果登录了，但是没有该角色，会抛出UnauthorizedException
    @RequiresRoles("python")
    @GetMapping("/python")
    public String python() {
        return "python programmer";
    }


    // 要求登录的用户具有js角色才能访问
    // 由于UserService模拟返回的用户信息中有该角色，所以这个接口可访问
    // 如果没有登录，UnauthenticatedException
    // 如果登录了，但是没有该角色，会抛出UnauthorizedException
    @RequiresRoles("admin")
    @GetMapping("/admin")
    public String admin() {
        return "admin user";
    }
}
