package com.github.oahnus.scaffold.auth.server.interceptor;

import com.github.oahnus.scaffold.common.annotation.NotNeedAuth;
import com.github.oahnus.scaffold.common.constants.Constants;
import com.github.oahnus.scaffold.common.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

/**
 * Created by oahnus on 2018/9/18
 * 17:37.
 */
@Slf4j
public class UserAuthInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(Constants.HEADER_TOKEN);

//        if (StringUtils.isBlank(token)) {
//            Optional<Cookie> optional = Arrays.stream(request.getCookies())
//                    .filter(cookie -> cookie.getName().equals(Constants.HEADER_TOKEN))
//                    .findFirst();
//            if (!optional.isPresent()) {
//                throw new AuthException("No Auth To Access");
//            }
//            token = optional.get().getValue();
//        }
        // todo jwt handle token , get info from token

        log.info("[UserAuthInterceptor].preHandle - ");

        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
