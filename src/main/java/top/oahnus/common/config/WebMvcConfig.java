package top.oahnus.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import top.oahnus.common.resolver.UserIdResolver;

import java.util.List;

/**
 * Created by oahnus on 2018/4/4
 * 14:26.
 */
@Component
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private UserIdResolver userIdResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userIdResolver);
    }
}
