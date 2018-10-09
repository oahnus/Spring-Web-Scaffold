package com.github.oahnus.scaffold.common.exception;

/**
 * Created by oahnus on 2017/10/3
 * 9:54.
 */
public class ClientException extends RuntimeException {
    public ClientException() {
    }

    public ClientException(String message) {
        super(message);
    }
}
