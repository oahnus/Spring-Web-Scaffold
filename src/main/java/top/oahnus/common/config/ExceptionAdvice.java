package top.oahnus.common.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import top.oahnus.common.dto.ResultDto;
import top.oahnus.common.enums.ErrorType;
import top.oahnus.common.exception.AuthException;
import top.oahnus.common.exception.DataNotFoundException;
import top.oahnus.common.exception.RequestParamInvalidException;
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
        e.printStackTrace();
        return new ResultDto(ErrorType.INTERNAL_SERVER_ERROR, "系统内部错误");
    }

    @ExceptionHandler(AuthException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResultDto processAuthException(AuthException e) {
        logger().error(e.getMessage());
        return new ResultDto(ErrorType.NO_AUTH, "未登录");
    }

    @ResponseBody
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResultDto processNotFoundException(DataNotFoundException e) {
        logger().error(e.getMessage());
        return new ResultDto(ErrorType.DATA_NOT_FOUND, "未登录");
    }

    @ResponseBody
    @ExceptionHandler(RequestParamInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultDto processBadRequestException(RequestParamInvalidException e) {
        logger().error(e.getMessage());
        return new ResultDto(ErrorType.REQUEST_PARAM_INVALID, "请求参数错误");
    }

}
