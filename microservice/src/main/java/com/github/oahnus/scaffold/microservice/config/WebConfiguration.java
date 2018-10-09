package com.github.oahnus.scaffold.microservice.config;

import com.github.oahnus.scaffold.auth.client.interceptor.ServiceAuthRestInterceptor;
import com.github.oahnus.scaffold.auth.client.interceptor.SysUserRestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by oahnus on 2018/10/8
 * 10:56.
 */
@Configuration
@Primary
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getServiceAuthRestInterceptor())
                .addPathPatterns(getIncludePathPatterns())
                .addPathPatterns("/api/sysuser/validate");
        registry.addInterceptor(getSysUserRestInterceptor())
                .addPathPatterns(getIncludePathPatterns());
    }

    @Bean
    SysUserRestInterceptor getSysUserRestInterceptor() {
        return new SysUserRestInterceptor();
    }

    @Bean
    ServiceAuthRestInterceptor getServiceAuthRestInterceptor() {
        return new ServiceAuthRestInterceptor();
    }

    private ArrayList<String> getIncludePathPatterns() {
        ArrayList<String> list = new ArrayList<>();
        String[] urls = {
                "/microservice/**"
        };
        Collections.addAll(list, urls);
        return list;
    }
}
