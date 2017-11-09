package top.oahnus.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import top.oahnus.interfaces.LoggerMixin;


/**
 * Created by oahnus on 2017/11/9
 * 15:20.
 */
@Component
@Aspect
public class RuntimeAspect implements LoggerMixin {
    @Pointcut("execution(* top.oahnus.service.*.*(..))")
    public void executeMethod() {}

    @Around("executeMethod()")
    public Object before(ProceedingJoinPoint joinPoint) throws Throwable {
        Long start = System.currentTimeMillis();
        Object retVal = joinPoint.proceed();
        Long end = System.currentTimeMillis() - start;

        String info = "execute " + joinPoint.getTarget() + " " + end + " ms";
        logger().debug(info);

        return retVal;
    }
}
