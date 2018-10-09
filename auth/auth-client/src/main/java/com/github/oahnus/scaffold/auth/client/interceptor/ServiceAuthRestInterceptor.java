package com.github.oahnus.scaffold.auth.client.interceptor;

import com.github.oahnus.scaffold.auth.client.config.ServiceAuthConfig;
import com.github.oahnus.scaffold.auth.client.jwt.ServiceAuthUtil;
import com.github.oahnus.scaffold.auth.common.jwt.JwtInfo;
import com.github.oahnus.scaffold.auth.common.jwt.JwtUtils;
import com.github.oahnus.scaffold.common.exception.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by oahnus on 2018/10/8
 * 10:58.
 */
@Slf4j
public class ServiceAuthRestInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    ServiceAuthConfig serviceAuthConfig;
    @Autowired
    ServiceAuthUtil serviceAuthUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("[ServiceAuthRestInterceptor].preHandle - Auth Client Service Auth In");
        String token = request.getHeader(serviceAuthConfig.getTokenHeader());
        if (StringUtils.isBlank(token)) {
            throw new ClientException("Client Is Forbidden");
        }
        JwtInfo jwtInfo = JwtUtils.getClientInfoFromToken(token);
        if (jwtInfo == null) {
            throw new ClientException("Client Is Forbidden");
        }
        String username = jwtInfo.getUsername();

        log.info("[ServiceAuthRestInterceptor].preHandle - {}", serviceAuthUtil.getAllowedClients());

        if (!serviceAuthUtil.getAllowedClients().contains(username)) {
            throw new ClientException("Client Is Forbidden");
        }
        // todo 检查服务调用权限
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("[ServiceAuthRestInterceptor].afterCompletion - Auth Client Service Auth Out");
        super.afterCompletion(request, response, handler, ex);
    }
}
