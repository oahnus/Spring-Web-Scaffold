package com.github.oahnus.scaffold.auth.server.web;

import com.github.oahnus.scaffold.auth.common.AuthPayload;
import com.github.oahnus.scaffold.common.dto.ResponseData;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by oahnus on 2018/9/28
 * 18:05.
 */
@RequestMapping("/jwt")
@RestController
public class JwtController {

    @PostMapping("/token")
    public ResponseData token(@RequestBody @Validated AuthPayload authPayload) {
        String username = authPayload.getUsername();
        String password = authPayload.getPassword();




        return new ResponseData();
    }
}
