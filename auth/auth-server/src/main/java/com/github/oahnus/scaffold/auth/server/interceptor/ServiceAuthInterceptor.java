package com.github.oahnus.scaffold.auth.server.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by oahnus on 2018/9/30
 * 15:32.
 */
@Slf4j
public class ServiceAuthInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("[ServiceAuthInterceptor].preHandle - In ServiceAuthInterceptor");
        return super.preHandle(request, response, handler);
    }
}
