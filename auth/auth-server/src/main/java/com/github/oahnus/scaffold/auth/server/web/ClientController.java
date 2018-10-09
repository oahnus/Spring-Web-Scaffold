package com.github.oahnus.scaffold.auth.server.web;

/**
 * Created by oahnus on 2018/9/29
 * 10:36.
 */
import com.github.oahnus.scaffold.auth.common.jwt.JwtClientInfo;
import com.github.oahnus.scaffold.auth.common.jwt.JwtUtils;
import com.github.oahnus.scaffold.common.dto.ResponseData;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by oahnus on 2018/9/29
 * 10:36.
 */
@RestController
@CrossOrigin
@RequestMapping("/client")
public class ClientController {

    @PostMapping("/token")
    public ResponseData token(@RequestParam String clientId, @RequestParam String secret) {
        String token = JwtUtils.generateTokenFromClientInfo(new JwtClientInfo(clientId, clientId, clientId));
        return new ResponseData<String>().data(token);
    }

    @RequestMapping(value = "/myClient")
    public ResponseData getAllowedClient(String serviceId, String secret) {
        // TODO 获取 AllowedClients 列表
        List<String> allowedClients = Arrays.asList("scaffold-auth", "scaffold-micro", "scaffold-gateway");
        return new ResponseData<List<String>>().data(allowedClients);
    }

    @RequestMapping(value = "/servicePubKey",method = RequestMethod.POST)
    public ResponseData getServicePublicKey(@RequestParam("clientId") String clientId, @RequestParam("secret") String secret) throws Exception {
//        authClientService.validate(clientId, secret);
        return new ResponseData();
    }

    @RequestMapping(value = "/sysuserPubKey",method = RequestMethod.POST)
    public ResponseData getUserPublicKey(@RequestParam("clientId") String clientId, @RequestParam("secret") String secret) throws Exception {
//        authClientService.validate(clientId, secret);
        return new ResponseData();
    }
}

