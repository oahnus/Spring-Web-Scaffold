package com.github.oahnus.scaffold.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Created by oahnus on 2019/4/7
 * 22:11.
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.github.oahnus.scaffold.domain.mapper"})
public class WebSiteApplication {
    public static void main(String... args) {
        SpringApplication.run(WebSiteApplication.class, args);
    }
}
