package top.oahnus.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


/**
 * Created by oahnus on 2017/11/9
 * 15:20.
 */
@Component
@Aspect
@Slf4j
public class RuntimeAspect {
    @Pointcut("@annotation(top.oahnus.common.annotations.RunTime)")
    public void executeMethod() {}

    @Around("executeMethod()")
    public Object before(ProceedingJoinPoint joinPoint) throws Throwable {
        Long start = System.currentTimeMillis();
        Object retVal = joinPoint.proceed();
        Long time = System.currentTimeMillis() - start;

        log.debug("[RuntimeAspect] - method: {}, runtime: {} ms", joinPoint.getTarget(), time);
        return retVal;
    }
}
