package top.oahnus.controller.open;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.oahnus.common.annotations.UserId;
import top.oahnus.common.dto.TokenDto;
import top.oahnus.common.payload.AuthPayload;
import top.oahnus.controller.mixin.ControllerMixIn;
import top.oahnus.security.TokenUtils;
import top.oahnus.service.AuthService;

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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public String post(@Validated @RequestBody AuthPayload payload) {
        // ////////////////////
        // 1. 使用自定义token, 使用Cookie保存客户端token， Redis保存Server端token
//        TokenDto tokenDto =  authService.login(payload);
//        Cookie tokenCookie = new Cookie("token", tokenDto.getToken());
//        response().addCookie(tokenCookie);
//        return tokenDto;
        // ////////////////////

        // ////////////////////////////
        // 2. Spring Security + jwt 做权限验证
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        payload.getUsername(),
                        payload.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-authentication so we can generate token
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(payload.getUsername());
        String token = this.tokenUtils.generateToken(userDetails);

        // Return the token
        return token;
        // /////////////////////////////
    }

    @GetMapping("/test")
    public String test(@UserId Long userId) {
        return String.valueOf(userId);
    }
}
