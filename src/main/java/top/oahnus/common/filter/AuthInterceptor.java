package top.oahnus.common.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import top.oahnus.common.annotations.OpenAccess;
import top.oahnus.common.constants.Message;
import top.oahnus.common.exception.AuthException;
import top.oahnus.service.session.RedisSessionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by oahnus on 2017/10/3
 * 11:50.
 */
@Component
@Slf4j
public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private RedisSessionService sessionService;

    @Value("${open.package}")
    private String openPackageName;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String option = request.getMethod();

        String origin = request.getHeader("Origin");
        log.debug("origin = {}", origin);
        log.debug("host = {}", request.getHeader("Host"));

        if ("OPTIONS".equals(option)){
            return true;
        }

//        HandlerMethod method = (HandlerMethod) handler;
//
//        Class<?> controllerClazz = method.getBean().getClass();
//        String packageName = controllerClazz.getPackage().getName();
//
//        OpenAccess access = method.getMethodAnnotation(OpenAccess.class);
//
//        if (access != null || packageName.equals(openPackageName)) {
//            return true;
//        }
//        if (sessionService == null) {
//            //解决service为null无法注入问题
//            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
//            sessionService = (RedisSessionService) factory.getBean("redisSessionService");
//        }
//        Long userId = sessionService.getUserId(request.getHeader("TOKEN"));
//        if (userId == null) {
//            throw new AuthException(Message.NO_AUTH);
//        }
        return true;
    }
}
