package com.github.oahnus.scaffold.microservice;

import com.github.oahnus.scaffold.auth.client.EnableAuthClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by oahnus on 2018/9/30
 * 15:15.
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaClient
@EnableFeignClients({"com.github.oahnus.scaffold.auth.client.feign"})
@EnableAuthClient
public class MicroServiceApplication {
    public static void main(String... args) {
        SpringApplication.run(MicroServiceApplication.class, args);
    }
}
