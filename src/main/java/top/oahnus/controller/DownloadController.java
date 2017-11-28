package top.oahnus.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.oahnus.common.annotations.Download;
import top.oahnus.common.annotations.OpenAccess;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * Created by oahnus on 2017/10/24
 * 9:59.
 */
@RestController
@CrossOrigin
@RequestMapping("/download")
public class DownloadController {

    @GetMapping("/test")
    @OpenAccess
    @Download
    public File downloadTest (HttpServletResponse response, HttpServletRequest request) {
        return new File("src/main/resources/application.yaml");
    }

    @GetMapping("/readme")
    @OpenAccess
    @Download
    public File downloadTest2(HttpServletResponse response) {
        return new File("src/main/resources/application-dev.yaml");
    }

}
