package com.github.oahnus.scaffold.web.config.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;


/**
 * Created by oahnus on 2018/8/1
 * 15:01.
 */
@ControllerAdvice
@Slf4j
public class CustomResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    private static final List<String> ignoreMethods = Collections.emptyList();

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        log.debug("MyResponseBodyAdvice==>supports: {}", aClass);
        log.debug("MyResponseBodyAdvice==>supports: {}", methodParameter.getClass());
        log.debug("MyResponseBodyAdvice==>supports: {}", MappingJackson2HttpMessageConverter.class.isAssignableFrom(aClass));
        //获取当前处理请求的controller的方法
        Method handleMethod = methodParameter.getMethod();
        if (handleMethod == null) {
            return false;
        }
        String methodName= handleMethod.getName();
        return ignoreMethods.contains(methodName);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        // 对body 进行包装

        return body;
    }
}
