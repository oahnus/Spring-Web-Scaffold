package top.oahnus.common.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import top.oahnus.common.annotations.OpenAccess;
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

    @Value("${open.package}")
    private String openPackageName;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String option = request.getMethod();
        if ("OPTIONS".equals(option)){
            return true;
        }

        HandlerMethod method = (HandlerMethod) handler;

        Class<?> controllerClazz = method.getBean().getClass();
        String packageName = controllerClazz.getPackage().getName();

        OpenAccess access = method.getMethodAnnotation(OpenAccess.class);

        if (access != null || packageName.equals(openPackageName)) {
            return true;
        }
//        if (sessionService == null) {//解决service为null无法注入问题
//            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
//            sessionService = (SessionService) factory.getBean("sessionService");
//        }
//        Long userId = sessionService.getUserId(request.getHeader("TOKEN"));
//        if (userId == null) {
//            throw new AuthException("无权限");
//        }
        return true;
    }
}
