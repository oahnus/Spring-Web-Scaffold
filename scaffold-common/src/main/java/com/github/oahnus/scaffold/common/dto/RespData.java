package com.github.oahnus.scaffold.common.dto;

import lombok.Data;

/**
 * Created by oahnus on 2019/4/24
 * 13:41.
 */
@Data
public class RespData {
    private Integer code;
    private String msg;
    private Object data;

    public RespData() {
        this.code = 200;
        this.msg = "success";
    }

    public RespData data(Object data) {
        this.data = data;
        return this;
    }

    public RespData error(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }
}
