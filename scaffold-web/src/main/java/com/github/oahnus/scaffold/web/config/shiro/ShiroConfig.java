package com.github.oahnus.scaffold.web.config.shiro;

import com.github.oahnus.scaffold.web.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * Created by oahnus on 2019/4/24
 * 16:24.
 */
@Slf4j
@Configuration
@AutoConfigureAfter(RabbitMQConfig.class)
public class ShiroConfig {
    private List<String> urlFilterList = new ArrayList<>();

    @Bean
    public Realm realm() {
        return new CustomRealm();
    }

    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        /**
         * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下。
         * 在@Controller注解的类的方法中加入@RequiresRole注解，会导致该方法无法映射请求，导致返回404。
         * 加入这项配置能解决这个bug
         */
        creator.setUsePrefix(true);
        return creator;
    }

    @Bean(name = "sessionManager")
    public DefaultWebSessionManager getDefaultWebSessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        // 设置session过期时间
        sessionManager.setGlobalSessionTimeout(60*60*1000);
        // 请注意看代码
        sessionManager.setSessionDAO(getMemorySessionDAO());
        return sessionManager;
    }


    // 配置sessionDAO
    @Bean(name="sessionDAO")
    public MemorySessionDAO getMemorySessionDAO(){
        MemorySessionDAO sessionDAO = new MemorySessionDAO();
        return sessionDAO;
    }


    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put(DefaultFilter.authc.toString(), new WebUserFilter());
        filterMap.put(DefaultFilter.perms.toString(), new WebPermissionsAuthorizationFilter());
        filterMap.put(DefaultFilter.logout.toString(), new WebLogoutFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        StringBuilder stringBuilder = new StringBuilder();
        urlFilterList.forEach(s -> stringBuilder.append(s).append("\n"));
        shiroFilterFactoryBean.setFilterChainDefinitions(stringBuilder.toString());

        return shiroFilterFactoryBean;
    }


    /**
     * 这里统一做鉴权，即判断哪些请求路径需要用户登录，哪些请求路径不需要用户登录。
     * 这里只做鉴权，不做权限控制，因为权限用注解来做。
     * @return
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chain = new DefaultShiroFilterChainDefinition();
        //哪些请求可以匿名访问
        chain.addPathDefinition("/auth/login", "anon");
        chain.addPathDefinition("/page/401", "anon");
        chain.addPathDefinition("/page/403", "anon");
        chain.addPathDefinition("/test/hello", "anon");
        chain.addPathDefinition("/test/guest", "anon");

        //除了以上的请求外，其它请求都需要登录
        chain.addPathDefinition("/**", "authc");
        return chain;
    }

    /**
     * 重写用户filter
     * <p>
     * shiro 默认 {@link org.apache.shiro.web.filter.authc.UserFilter}
     *
     * @author seer
     * @date 2018/6/17 22:30
     */
    class WebUserFilter extends AccessControlFilter {
        @Override
        protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
            response.setContentType("application/json");
            if (isLoginRequest(request, response)) {
                return true;
            }

            Subject subject = getSubject(request, response);
            if (subject.getPrincipal() != null) {
                return true;
            }
            response.getWriter().write("{\"response_code\":\"9000\",\"response_msg\":\"登录过期\"}");
            return false;
        }

        /**
         * 不要做任何处理跳转，直接return，进行下一个filter
         *
         * @param request
         * @param response
         * @return
         * @throws Exception
         */
        @Override
        protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
            return false;
        }
    }

    /**
     * 重写权限filter
     * <p>
     * shiro 默认 {@link org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter}
     * <p>
     * 前后端分离项目，直接获取url进行匹配，后台配置的权限的值就是请求路径
     *
     * @author seer
     * @date 2018/6/17 22:41
     */
    class WebPermissionsAuthorizationFilter extends AuthorizationFilter {
        @Override
        protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
            Subject subject = getSubject(request, response);
            HttpServletRequest httpServletRequest = ((HttpServletRequest) request);
            String url = httpServletRequest.getServletPath();
            if (subject.isPermitted(url)) {
                return true;
            }
            response.getWriter().write("{\"response_code\":\"90001\",\"response_msg\":\"权限不足\"}");
            return false;
        }

        @Override
        protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
            return false;
        }
    }

    /**
     * 重写登出filter
     * shiro 默认 {@link LogoutFilter}
     *
     * @author seer
     * @date 2018/6/26 2:09
     */
    class WebLogoutFilter extends LogoutFilter {
        @Override
        protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
            response.getWriter().write("{\"response_code\":\"0000\",\"response_msg\":\"SUCCES\"}");
            Subject subject = getSubject(request, response);

            if (isPostOnlyLogout()) {
                if (!WebUtils.toHttp(request).getMethod().toUpperCase(Locale.ENGLISH).equals(HttpMethod.POST.toString())) {
                    return onLogoutRequestNotAPost(request, response);
                }
            }
            try {
                subject.logout();
            } catch (SessionException ise) {
                log.trace("Encountered session exception during logout.  This can generally safely be ignored.", ise);
            }
            return false;
        }
    }
}
