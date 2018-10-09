package com.github.oahnus.scaffold.auth.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by oahnus on 2018/10/8
 * 10:02.
 */
@Configuration
@ComponentScan("com.github.oahnus.scaffold.auth.client")
public class AuthConfiguration {
    @Bean
    SysUserAuthConfig sysUserAuthConfig() {
        return new SysUserAuthConfig();
    }

    @Bean
    ServiceAuthConfig serviceAuthConfig() {
        return new ServiceAuthConfig();
    }
}
