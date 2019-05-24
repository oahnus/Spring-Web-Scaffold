package com.github.oahnus.scaffold.common.exceptions;

/**
 * Created by oahnus on 2019/4/19
 * 13:25.
 */
public class ClientException extends RuntimeException {
    public ClientException() {
    }

    public ClientException(String message) {
        super(message);
    }
}
