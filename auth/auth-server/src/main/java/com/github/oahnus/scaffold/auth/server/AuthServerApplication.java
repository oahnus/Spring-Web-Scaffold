package com.github.oahnus.scaffold.auth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by oahnus on 2018/9/6
 * 18:39.
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableFeignClients
@EnableEurekaClient
public class AuthServerApplication {
    public static void main(String... args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }
}
