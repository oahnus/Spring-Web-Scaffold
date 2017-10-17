package top.oahnus.controller;

import org.springframework.web.bind.annotation.*;
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
    public String test(@RequestParam("id") String id) {
        return id;
    }
}
