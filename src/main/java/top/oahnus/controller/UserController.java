package top.oahnus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.oahnus.common.config.OahnusConfig;
import top.oahnus.controller.mixin.ControllerMixIn;

/**
 * Created by oahnus on 2017/10/3
 * 0:57.
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController extends ControllerMixIn {
    @GetMapping("/test")
    public Long test() {
        return getUserId();
    }

    @Autowired
    private OahnusConfig oahnusConfig;

    @GetMapping("/config")
    public String configName(){
        return oahnusConfig.getName();
    }
}
