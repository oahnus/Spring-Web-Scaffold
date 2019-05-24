package com.github.oahnus.scaffold.common.exceptions;

/**
 * Created by oahnus on 2019/4/19
 * 13:25.
 */
public class InvalidRequestParamException extends ClientException {
    public InvalidRequestParamException() {
    }

    public InvalidRequestParamException(String message) {
        super(message);
    }
}
