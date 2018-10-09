package com.github.oahnus.scaffold.auth.server.config;

import com.github.oahnus.scaffold.auth.server.interceptor.ServiceAuthInterceptor;
import com.github.oahnus.scaffold.auth.server.interceptor.UserAuthInterceptor;
import com.github.oahnus.scaffold.common.handler.ExceptionAdvice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Created by oahnus on 2018/9/30
 * 17:30.
 */
@Configuration("WebConfig")
@Primary
public class WebConfiguration implements WebMvcConfigurer {
    @Bean
    ExceptionAdvice getExceptionAdvice() {
        return new ExceptionAdvice();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getServiceAuthInterceptor())
                .addPathPatterns("/service/**");
        registry.addInterceptor(getUserAuthInterceptor())
                .addPathPatterns("/service/**");

    }

    @Bean
    UserAuthInterceptor getUserAuthInterceptor() {
        return new UserAuthInterceptor();
    }

    @Bean
    ServiceAuthInterceptor getServiceAuthInterceptor() {
        return new ServiceAuthInterceptor();
    }
}
