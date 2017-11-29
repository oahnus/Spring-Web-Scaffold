package top.oahnus.common.filter;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import top.oahnus.common.annotations.OpenAccess;
import top.oahnus.interfaces.LoginStrategy;
import top.oahnus.strategy.NormalLoginStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by oahnus on 2017/10/3
 * 11:50.
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private LoginStrategy loginStrategy = new NormalLoginStrategy();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String option = request.getMethod();
        if ("OPTIONS".equals(option)){
            return true;
        }
        HandlerMethod method = (HandlerMethod) handler;
        OpenAccess access = method.getMethodAnnotation(OpenAccess.class);

        if (access != null) {
            return true;
        }

        return loginStrategy.verifyRequest(request, response, handler);
    }
}
