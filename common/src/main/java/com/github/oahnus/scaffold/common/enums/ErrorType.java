package com.github.oahnus.scaffold.common.enums;

/**
 * Created by oahnus on 2017/10/3
 * 13:19.
 */
public enum ErrorType {
    INTERNAL_SERVER_ERROR(50000),

    NO_AUTH(40100),
    NO_ACCESS(40101),

    DATA_NOT_FOUND(40401),

    REQUEST_PARAM_INVALID(40001),
    DATA_EXISTED(40002),
    BAD_REQUEST(40003),
    UNSUPPORTED_MEDIA(40004),
    INVALID_PARAM_ERROR(40005);

    private int code;

    ErrorType(int code) {
        this.code = code;
    }

    public static ErrorType getErrorType(int code) {
        for (ErrorType type: values()) {
            if (type.code == code) {
                return type;
            }
        }
        return null;
    }

    public int getCode() {
        return this.code;
    }
}
