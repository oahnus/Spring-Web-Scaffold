package top.oahnus.common.dto;

import lombok.Data;
import top.oahnus.common.enums.ErrorType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by oahnus on 2017/10/3
 * 0:52.
 */
@Data
public class ResultData {
    private Integer code;
    private String msg;
    private Map<String, Object> data = new HashMap<>();

    public ResultData(ErrorType type, String msg) {
        this.code = type.ordinal();
        this.msg = msg;
    }

    public ResultData() {
        this.code = 0;
        this.msg = "success";
    }

    public ResultData data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
}
