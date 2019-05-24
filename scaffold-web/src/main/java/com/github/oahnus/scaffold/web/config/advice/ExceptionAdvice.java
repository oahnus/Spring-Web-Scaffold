package com.github.oahnus.scaffold.web.config.advice;

import com.github.oahnus.scaffold.common.dto.RespData;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by oahnus on 2019/4/24
 * 13:40.
 */
@ControllerAdvice
@Slf4j
@ResponseBody
public class ExceptionAdvice {
    private static final String GUEST_ONLY = "Attempting to perform a guest-only operation";

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RespData processException(Exception e) throws Exception {
        log.error(e.getMessage());
        e.printStackTrace();
        return new RespData().error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ExceptionHandler(ShiroException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public RespData handleShiroException(ShiroException e) {
        String eName = e.getClass().getSimpleName();
        log.error("shiro执行出错：{}",eName);
        e.printStackTrace();
        return new RespData().error(HttpStatus.UNAUTHORIZED.value(), eName);
    }

    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public RespData page401(UnauthenticatedException e) {
        String eMsg = e.getMessage();
        if (StringUtils.startsWithIgnoreCase(eMsg, GUEST_ONLY)){
            return new RespData().error(HttpStatus.UNAUTHORIZED.value(),"只允许游客访问，若您已登录，请先退出登录" );
        }else{
            return new RespData().error(HttpStatus.UNAUTHORIZED.value(),"用户未登录" );
        }
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public RespData page403(UnauthorizedException e) {
        return new RespData().error(HttpStatus.FORBIDDEN.value(), "无权限访问");
    }

}
