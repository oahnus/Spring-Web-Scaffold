package top.oahnus.strategy;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import top.oahnus.common.constants.Constants;
import top.oahnus.common.exception.AuthException;
import top.oahnus.interfaces.LoginStrategy;
import top.oahnus.service.session.EhcacheSessionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by oahnus on 2017/11/29
 * 11:09.
 */
public class NormalLoginStrategy implements LoginStrategy {
    @Autowired
    private EhcacheSessionService sessionService;

    @Override
    public boolean verifyRequest(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if (sessionService == null) {//解决service为null无法注入问题
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            sessionService = (EhcacheSessionService) factory.getBean("ehcacheSessionService");
        }
        Long userId = sessionService.getUserId(request.getHeader(Constants.TOKEN));
        if (userId == null) {
            throw new AuthException("无权限");
        }
        return true;
    }
}
