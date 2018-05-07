package top.oahnus.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import top.oahnus.common.constants.Message;
import top.oahnus.common.dto.ResultData;
import top.oahnus.common.enums.ErrorType;
import top.oahnus.common.exception.AuthException;
import top.oahnus.common.exception.DataNotFoundException;
import top.oahnus.common.exception.RequestParamInvalidException;

/**
 * Created by oahnus on 2017/10/3
 * 13:11.
 */
@ControllerAdvice
@Slf4j
public class ExceptionAdvice {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResultData processException(Exception e) throws Exception {
        log.error(e.getMessage());
        e.printStackTrace();
        return new ResultData(ErrorType.INTERNAL_SERVER_ERROR, Message.INNER_SERVER_ERROR);
    }

    @ExceptionHandler(AuthException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResultData processAuthException(AuthException e) {
        log.error(e.getMessage());
        return new ResultData(ErrorType.NO_AUTH, Message.NO_AUTH);
    }

    @ResponseBody
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResultData processNotFoundException(DataNotFoundException e) {
        log.error(e.getMessage());
        return new ResultData(ErrorType.DATA_NOT_FOUND, Message.DATA_NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(RequestParamInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultData processBadRequestException(RequestParamInvalidException e) {
        log.error(e.getMessage());
        return new ResultData(ErrorType.REQUEST_PARAM_INVALID, Message.BAD_REQUEST);
    }

}
