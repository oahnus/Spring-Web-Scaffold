package top.oahnus.common.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import top.oahnus.common.annotations.UserId;
import top.oahnus.service.SessionService;

/**
 * Created by oahnus on 2018/4/4
 * 14:20.
 */
@Component
public class UserIdResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private SessionService sessionService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(UserId.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        return sessionService.getUserId(sessionService.getToken());
    }
}
