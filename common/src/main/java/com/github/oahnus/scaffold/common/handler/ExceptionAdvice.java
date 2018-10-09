package com.github.oahnus.scaffold.common.handler;

import com.github.oahnus.scaffold.common.constants.Message;
import com.github.oahnus.scaffold.common.dto.ResponseData;
import com.github.oahnus.scaffold.common.enums.ErrorType;
import com.github.oahnus.scaffold.common.exception.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import com.github.oahnus.scaffold.common.exception.AuthException;
import com.github.oahnus.scaffold.common.exception.DataNotFoundException;
import com.github.oahnus.scaffold.common.exception.RequestParamInvalidException;

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
    public ResponseData processException(Exception e) throws Exception {
        log.error(e.getMessage());
        e.printStackTrace();
        return new ResponseData(ErrorType.INTERNAL_SERVER_ERROR, Message.INNER_SERVER_ERROR);
    }

    @ExceptionHandler(AuthException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseData processAuthException(AuthException e) {
        log.error(e.getMessage());
        return new ResponseData(ErrorType.NO_AUTH, Message.NO_AUTH);
    }

    @ExceptionHandler(ClientException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseData processClientException(ClientException e) {
        log.error(e.getMessage());
        return new ResponseData(ErrorType.BAD_REQUEST, e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseData processNotFoundException(DataNotFoundException e) {
        log.error(e.getMessage());
        return new ResponseData(ErrorType.DATA_NOT_FOUND, Message.DATA_NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(RequestParamInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseData processBadRequestException(RequestParamInvalidException e) {
        log.error(e.getMessage());
        return new ResponseData(ErrorType.REQUEST_PARAM_INVALID, Message.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseData processValidationError(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        return new ResponseData(ErrorType.BAD_REQUEST, e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseData processMethodArgumentTypeError(MethodArgumentTypeMismatchException e) {
        log.error(e.getMessage());
        return new ResponseData(ErrorType.BAD_REQUEST, e.getMessage());
    }

//    /**
//     * 请求方法不支持
//     *
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    public ResponseData processNotSupportMethodError(HttpRequestMethodNotSupportedException e) {
//        log.info("MissingServletRequestParameterException,e={}", e.getMessage());
//        return new ResponseData(ErrorType.INVALID_PARAM_ERROR, "请求方法不正确。");
//    }
//
//    /**
//     * MediaType 无法处理
//     *
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(value = HttpMediaTypeException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    public ResponseData processUnsupportedMediaError(HttpMediaTypeException e) {
//        log.info("HttpMediaTypeException,e={}", e.getMessage());
//        return new ResponseData(ErrorType.UNSUPPORTED_MEDIA, "请使用`JSON`格式传输数据。");
//    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public ResponseData processHttpRequestBodyError(HttpMessageNotReadableException e) {
        log.error(e.getMessage());
        return new ResponseData(ErrorType.INVALID_PARAM_ERROR, e.getMessage());
    }
}
