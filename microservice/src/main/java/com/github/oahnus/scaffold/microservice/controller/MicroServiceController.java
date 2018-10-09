package com.github.oahnus.scaffold.microservice.controller;

import com.github.oahnus.scaffold.common.dto.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by oahnus on 2018/10/1
 * 12:42.
 */
@RestController
@RequestMapping("microservice")
@Slf4j
public class MicroServiceController {
    @GetMapping("/test")
    public ResponseData test() {
        log.info("[MicroServiceController].test - API IN");
        return new ResponseData<String>().data("success");
    }
}
