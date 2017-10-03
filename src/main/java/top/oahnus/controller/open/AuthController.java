package top.oahnus.controller.open;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.oahnus.common.dto.TokenDto;
import top.oahnus.common.payload.AuthPayload;
import top.oahnus.domain.UserAuth;
import top.oahnus.service.AuthService;
import top.oahnus.service.SessionService;

/**
 * Created by oahnus on 2017/10/3
 * 0:59.
 */
@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private SessionService sessionService;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public TokenDto post(@Validated @RequestBody AuthPayload payload) {
        return authService.login(payload);
    }
}
