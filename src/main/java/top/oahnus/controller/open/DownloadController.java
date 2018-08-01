package top.oahnus.controller.open;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.oahnus.common.annotations.Download;
import top.oahnus.common.annotations.OpenAccess;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URL;

/**
 * Created by oahnus on 2017/10/24
 * 9:59.
 */
@RestController
@CrossOrigin
@RequestMapping("/download")
public class DownloadController {

    /**
     * @return 请求下载的文件对象
     */
    @GetMapping("/test")
    @Download
    public File downloadTest () {
        URL url = DownloadController.class.getClassLoader().getResource("");
        String filePath = url == null ? "" : url.getPath();
        if (StringUtils.isBlank(filePath)) {
            return null;
        }
        return new File(filePath + "/static/download.txt");
    }
}
