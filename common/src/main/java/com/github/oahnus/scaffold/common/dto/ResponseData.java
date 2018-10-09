package com.github.oahnus.scaffold.common.dto;

import com.github.oahnus.scaffold.common.enums.ErrorType;
import lombok.Data;

/**
 * Created by oahnus on 2017/10/3
 * 0:52.
 */
@Data
public class ResponseData<T> {
    private Integer code;
    private String msg;
    private T data;

    public ResponseData(ErrorType type, String msg) {
        this.code = type.getCode();
        this.msg = msg;
    }

    public ResponseData() {
        this.code = 0;
        this.msg = "success";
    }

    public ResponseData<T> data(T data) {
        this.data = data;
        return this;
    }
}
