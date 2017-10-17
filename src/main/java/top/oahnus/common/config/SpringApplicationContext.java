package top.oahnus.common.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by oahnus on 2017/10/17
 * 20:21.
 */
@Component
public class SpringApplicationContext implements ApplicationContextAware {
    private static ApplicationContext ac = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringApplicationContext.ac == null) {
            SpringApplicationContext.ac = applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext() {
        return ac;
    }

    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }
}
