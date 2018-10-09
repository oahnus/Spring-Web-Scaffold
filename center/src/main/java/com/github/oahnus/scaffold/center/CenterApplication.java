package com.github.oahnus.scaffold.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by oahnus on 2018/9/6
 * 17:45.
 */
@EnableEurekaServer
@SpringBootApplication
public class CenterApplication {
    public static void main(String... args) {
        SpringApplication.run(CenterApplication.class, args);
    }
}
