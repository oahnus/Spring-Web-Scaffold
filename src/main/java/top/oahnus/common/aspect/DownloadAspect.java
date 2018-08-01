package top.oahnus.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Created by oahnus on 2017/10/24
 * 用于支持Controller中标记了@Download注解的方法 实现http文件下载
 * 仅支持单文件下载
 * 10:31.
 */
@Component
@Aspect
@Slf4j
public class DownloadAspect {

    @Pointcut("@annotation(top.oahnus.common.annotations.Download)")
    public void download(){}

    @Around("download()")
    public Object before(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletResponse response = (HttpServletResponse) joinPoint.getArgs()[0];
        File file = (File) joinPoint.proceed();

        if (file == null) {
            log.info("[DownloadAspect] - Download file is null");
        } else {
            response.setContentType("multipart/form-data");
            response.addHeader("Content-Disposition",
                    "attachment; filename=" + URLEncoder.encode(file.getName(), "utf-8"));
            // FIXME
            // in Paths.get(uri) uri can't contain %20
            // this is java bug in jdk7(version below 7u40) and jdk8 (version below 8b36)
            // solution: make sure uri not contain special character or update jdk version
            response.getOutputStream().write(Files.readAllBytes(Paths.get(file.toURI())));
            response.getOutputStream().flush();
        }
        return null;
    }

    @Before("download()")
    public void doBefore(JoinPoint point) {
        log.debug("[DownloadAspect] - Download File");
    }
}
