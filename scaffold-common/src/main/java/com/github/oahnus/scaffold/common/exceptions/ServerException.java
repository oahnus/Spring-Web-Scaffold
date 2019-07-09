package com.github.oahnus.scaffold.common.exceptions;

/**
 * Created by oahnus on 2019/7/9
 * 9:42.
 */
public class ServerException extends RuntimeException {
    public ServerException() {
        super();
    }

    public ServerException(String message) {
        super(message);
    }
}
