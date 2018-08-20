package top.oahnus.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
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


    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResultData processAccessDeniedException(AccessDeniedException e) {
        log.error(e.getMessage());
        return new ResultData(ErrorType.NO_ACCESS, e.getMessage());
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
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultData processValidationError(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        return new ResultData(ErrorType.BAD_REQUEST, e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultData processMethodArgumentTypeError(MethodArgumentTypeMismatchException e) {
        log.error(e.getMessage());
        return new ResultData(ErrorType.BAD_REQUEST, e.getMessage());
    }

    /**
     * 请求方法不支持
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultData processNotSupportMethodError(HttpRequestMethodNotSupportedException e) {
        log.info("MissingServletRequestParameterException,e={}", e.getMessage());
        return new ResultData(ErrorType.INVALID_PARAM_ERROR, "请求方法不正确。");
    }

    /**
     * MediaType 无法处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = HttpMediaTypeException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultData processUnsupportedMediaError(HttpMediaTypeException e) {
        log.info("HttpMediaTypeException,e={}", e.getMessage());
        return new ResultData(ErrorType.UNSUPPORTED_MEDIA, "请使用`JSON`格式传输数据。");
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public ResultData processHttpRequestBodyError(HttpMessageNotReadableException e) {
        log.error(e.getMessage());
        return new ResultData(ErrorType.INVALID_PARAM_ERROR, e.getMessage());
    }
}
