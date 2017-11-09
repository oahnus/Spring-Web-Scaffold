package top.oahnus.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import top.oahnus.interfaces.LoggerMixin;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Created by oahnus on 2017/10/24
 * 10:31.
 */
@Component
@Aspect
public class DownloadAspect implements LoggerMixin {

    @Pointcut("@annotation(top.oahnus.common.annotations.Download)")
    public void download(){}

//    @Around("download()")
//    public Object downloadFile(ProceedingJoinPoint joinPoint) throws Throwable {
//        logger.info("enter");
//        Object[] args = joinPoint.getArgs();
//        HttpServletResponse response = (HttpServletResponse) args[0];
//        System.out.println(response.getContentType());
//        Object ret = joinPoint.proceed();
//        return ret;
//    }

    @Around("download()")
    public Object before(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletResponse response = (HttpServletResponse) joinPoint.getArgs()[0];
        File file = (File) joinPoint.proceed();

        if (file == null) {
            System.out.println("null");
        } else {
            response.setContentType("multipart/form-data");
            response.addHeader("Content-Disposition",
                    "attachment; filename=" + URLEncoder.encode(file.getName(), "utf-8"));
            response.getOutputStream().write(Files.readAllBytes(Paths.get(file.toURI())));
            response.getOutputStream().flush();
        }
        return null;
    }

    @Before("download()")
    public void doBefore(JoinPoint point) {
        logger().info("Download File");
    }
}
