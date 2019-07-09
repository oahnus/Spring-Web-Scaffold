package com.github.oahnus.scaffold.common.aspects;

import com.github.oahnus.scaffold.common.exceptions.ServerException;
import com.github.oahnus.scaffold.common.interfaces.HttpMixin;
import com.github.oahnus.scaffold.common.utils.DateUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 * Created by oahnus on 2017/10/24
 * 10:31.
 */
@Component
@Aspect
public class DownloadAspect implements HttpMixin {

    @Pointcut("@annotation(com.github.oahnus.scaffold.common.annotations.Download)")
    public void download(){}

    @Around("download()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletResponse response = response();

        Object retVal = joinPoint.proceed();
        if (retVal instanceof File) {
            File file = (File) retVal;
            addFile(response, file);
        } else if (retVal instanceof List) {
            @SuppressWarnings("unchecked")
            List<File> fileList = (List<File>) retVal;

            ZipOutputStream zos = null;
            try {
                String zipFilename = "Archive" + DateUtils.now()
                        .replaceAll(" ", "-")
                        .replaceAll(":", "-");

                File downloadFile = File.createTempFile(zipFilename, ".zip");
                zos = new ZipOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream(downloadFile)));
                for (File file : fileList) {
                    compress(zos, file);
                }
                zos.flush();
                zos.close(); // 不close, 下载的压缩文件会损坏
                zos = null;
                addFile(response, downloadFile);
            } catch (IOException e) {
                e.printStackTrace();
                throw new ServerException("Download Failed");
            } finally {
                if (zos != null) {
                    zos.close();
                }
            }
        } else {
            throw new ServerException("Download Failed");
        }

        return null;
    }

    private void addFile(HttpServletResponse response, File file) throws IOException {
        response.setContentType("multipart/form-data");
//        response.setContentType("application/octet-stream");
        response.addHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.addHeader("Content-Disposition",
                "attachment; filename=" + URLEncoder.encode(file.getName(), "utf-8"));

        byte[] bytes = Files.readAllBytes(Paths.get(file.toURI()));
        ServletOutputStream out = response.getOutputStream();

        out.write(bytes);
        out.flush();
        out.close();
    }

    private void compress(ZipOutputStream zos, File file) throws IOException {
        if (file == null || !file.exists()) {
            return;
        }
        String filename = file.getName();
        if (file.isFile()) {
            ZipEntry entry = new ZipEntry(filename);
            zos.putNextEntry(entry);

            byte[] bytes = Files.readAllBytes(Paths.get(file.toURI()));
            zos.write(bytes);

            zos.flush();
            zos.closeEntry();
        } else {
            // 文件夹
            File[] fs = file.listFiles();

            if (fs == null || fs.length == 0 ) {
                zos.putNextEntry(new ZipEntry(filename + File.separator ));
                zos.closeEntry();
                return ;
            }

            for (File f : fs) {
                compress(zos, f);
            }
        }
    }
}
