package top.oahnus.controller.open;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.oahnus.common.annotations.UserId;
import top.oahnus.common.dto.TokenDto;
import top.oahnus.common.payload.AuthPayload;
import top.oahnus.controller.mixin.ControllerMixIn;
import top.oahnus.domain.UserAuth;
import top.oahnus.service.AuthService;
import top.oahnus.service.SessionService;

import javax.servlet.http.Cookie;

/**
 * Created by oahnus on 2017/10/3
 * 0:59.
 */
@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController extends ControllerMixIn{
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public TokenDto post(@Validated @RequestBody AuthPayload payload) {
        TokenDto tokenDto =  authService.login(payload);
        Cookie tokenCookie = new Cookie("token", tokenDto.getToken());
        response().addCookie(tokenCookie);
        return tokenDto;
    }

    @GetMapping("/test")
    public String test(@UserId Long userId) {
        return String.valueOf(userId);
    }
}
