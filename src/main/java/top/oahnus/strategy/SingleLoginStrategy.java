package top.oahnus.strategy;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import top.oahnus.common.constants.Constants;
import top.oahnus.common.exception.AuthException;
import top.oahnus.interfaces.LoginStrategy;
import top.oahnus.service.session.EhcacheSessionService;
import top.oahnus.service.session.RedisSessionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by oahnus on 2017/11/29
 * 11:09.
 */
public class SingleLoginStrategy implements LoginStrategy {
    @Autowired
    private RedisSessionService sessionService;

    @Override
    public boolean verifyRequest(HttpServletRequest request, HttpServletResponse response, Object handler) {

        HandlerMethod method = (HandlerMethod) handler;

        if (sessionService == null) {//解决service为null无法注入问题
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            sessionService = (RedisSessionService) factory.getBean("redisSessionService");
        }
        Long userId = Long.valueOf(request.getHeader(Constants.USER_ID));

        String token = sessionService.getToken(userId, request.getHeader(Constants.TOKEN));
        if (token == null) {
            throw new AuthException("无权限");
        }

        return true;
    }
}
