package com.github.oahnus.scaffold.web.rest;

import com.github.oahnus.scaffold.common.dto.RespData;
import com.github.oahnus.scaffold.domain.entity.UserAuth;
import com.github.oahnus.scaffold.web.payload.LoginPayload;
import com.github.oahnus.scaffold.web.service.AuthService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by oahnus on 2019/4/24
 * 16:42.
 */
@RequestMapping("/auth")
@RestController
public class AuthController {
    @Autowired
    AuthService authService;


    @PostMapping("/login")
    public RespData login(@RequestBody LoginPayload payload) {
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(payload.getUsername(), payload.getPassword());
        // 执行认证登陆
        subject.login(token);
        //根据权限，指定返回数据
        UserAuth userAuth = (UserAuth) subject.getPrincipals().getPrimaryPrincipal();
        return new RespData().data(userAuth);
    }

    @GetMapping("/logout")
    public RespData logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
        }
        return new RespData();
    }
}
