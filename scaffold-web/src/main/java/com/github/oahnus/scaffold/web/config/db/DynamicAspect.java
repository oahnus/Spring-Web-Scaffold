package com.github.oahnus.scaffold.web.config.db;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by oahnus on 2019/10/22
 * 21:59.
 */
@Aspect
@Component
public class DynamicAspect implements Ordered {
    @Pointcut("@annotation(com.github.oahnus.scaffold.web.config.db.TargetDataSource)")
    public void pointCut() {}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();

        TargetDataSource targetDataSource = method.getAnnotation(TargetDataSource.class);
        String name = targetDataSource.name();

        DynamicDataSource.setDataSource(name);

        try {
            return pjp.proceed();
        } catch (Exception e) {
            throw e;
        } finally {
            DynamicDataSource.clearDataSource();
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
