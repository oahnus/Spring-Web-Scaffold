package top.oahnus.common.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import top.oahnus.common.dto.ResultDto;
import top.oahnus.common.enums.ErrorType;
import top.oahnus.interfaces.LoggerMixin;

/**
 * Created by oahnus on 2017/10/3
 * 13:11.
 */
@ControllerAdvice
public class ExceptionAdvice implements LoggerMixin {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResultDto processException(Exception e) throws Exception {
        logger().error(e.getMessage());
        return new ResultDto(ErrorType.INTERNAL_SERVER_ERROR, "系统内部错误");
    }
}
