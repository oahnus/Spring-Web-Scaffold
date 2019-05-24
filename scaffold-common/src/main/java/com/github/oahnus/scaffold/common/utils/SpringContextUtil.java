package com.github.oahnus.scaffold.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Created by oahnus on 2018/8/10
 * 13:52.
 */
@Component
@Lazy(false)
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.context = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return SpringContextUtil.context;
    }

    public static Object getBean(String name) {
        return SpringContextUtil.context.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return SpringContextUtil.context.getBean(clazz);
    }
}
