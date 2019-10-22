package com.github.oahnus.scaffold.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Created by oahnus on 2019/4/7
 * 22:11.
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)  // 配置多数据源时， 要exclude此类，否则会出现循环依赖的问题
@ComponentScan(basePackages = "com.github.oahnus.scaffold")
@MapperScan(basePackages = {"com.github.oahnus.scaffold.domain.mapper"})
public class WebSiteApplication {
    public static void main(String... args) {
        SpringApplication.run(WebSiteApplication.class, args);
    }
}
