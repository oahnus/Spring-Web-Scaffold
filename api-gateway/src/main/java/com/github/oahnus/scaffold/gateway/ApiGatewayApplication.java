package com.github.oahnus.scaffold.gateway;

import com.github.oahnus.scaffold.auth.client.EnableAuthClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by oahnus on 2018/9/30
 * 16:02.
 */
@EnableFeignClients({
        "com.github.oahnus.scaffold.auth.client.feign"
})
@EnableAuthClient
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ApiGatewayApplication {
    public static void main(String... args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
