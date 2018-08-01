package top.oahnus.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * Created by oahnus on 2018/8/1
 * 15:01.
 */
@ControllerAdvice
@Slf4j
public class CustomResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        log.debug("MyResponseBodyAdvice==>supports: {}", aClass);
        log.debug("MyResponseBodyAdvice==>supports: {}", methodParameter.getClass());
        log.debug("MyResponseBodyAdvice==>supports: {}", MappingJackson2HttpMessageConverter.class.isAssignableFrom(aClass));
        //获取当前处理请求的controller的方法
        String methodName= methodParameter.getMethod().getName();
        // 不拦截/不需要处理返回值 的方法
        String method= "loginCheck"; //如登录
        //不拦截
        return !method.equals(methodName);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        // 对body 进行包装

        return body;
    }
}
