package top.oahnus.controller.open;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.oahnus.common.dto.ResultData;

/**
 * Created by oahnus on 2018/5/7
 * 16:52.
 */
@RestController
@RequestMapping("/file/upload")
public class FileUploadController {

    @PostMapping("/normal")
    public ResultData uploadNormalFile(@RequestBody MultipartFile multipartFile) {
        return new ResultData();
    }

    @PostMapping("/big")
    public ResultData uploadBigFile() {
        return new ResultData();
    }
}
