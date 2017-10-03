package top.oahnus.common.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by oahnus on 2017/10/3
 * 0:52.
 */
@Data
public class ResultDto {
    private Integer code;
    private String msg;
    private Map<String, Object> data = new HashMap<>();

    public ResultDto data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
}
