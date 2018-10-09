package com.github.oahnus.scaffold.auth.client.interceptor;

import com.github.oahnus.scaffold.auth.client.config.SysUserAuthConfig;
import com.github.oahnus.scaffold.auth.client.jwt.SysUserAuthUtil;
import com.github.oahnus.scaffold.auth.common.jwt.JwtInfo;
import com.github.oahnus.scaffold.auth.common.jwt.JwtUtils;
import com.github.oahnus.scaffold.common.context.RequestContext;
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
public class SysUserRestInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private SysUserAuthConfig sysUserAuthConfig;
    @Autowired
    private SysUserAuthUtil sysUserAuthUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("[SysUserRestInterceptor].preHandle - Auth Client SysUser In");
        String token = request.getHeader(sysUserAuthConfig.getTokenHeader());
        if (StringUtils.isBlank(token)) {
            throw new ClientException("User Auth Is Forbidden");
        }
//        JwtInfo jwtInfo = JwtUtils.getJwtUserInfoFromToken(token);
//        // todo 检查用户token权限
//        if (jwtInfo == null) {
//            throw new ClientException("User Auth Is Forbidden");
//        }
//        String username = jwtInfo.getUsername();
//
//        RequestContext.setUsername(username);
//        RequestContext.setToken(token);

        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("[SysUserRestInterceptor].afterCompletion - Auth Client SysUser Out");
        RequestContext.clear();
        super.afterCompletion(request, response, handler, ex);
    }

}
