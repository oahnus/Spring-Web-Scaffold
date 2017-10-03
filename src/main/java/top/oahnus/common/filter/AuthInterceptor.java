package top.oahnus.common.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import top.oahnus.service.SessionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by oahnus on 2017/10/3
 * 11:50.
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private SessionService sessionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        sessionService.getSessionToken();
        System.out.println("pre");
        return true;
    }
}
