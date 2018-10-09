package com.github.oahnus.scaffold.common.enums;

/**
 * Created by oahnus on 2018/8/24
 * 14:18.
 */
public enum Sex {
    DEFAULT(0),
    FEMALE(1),
    MALE(2);

    private int code;

    Sex(int code) {
        this.code = code;
    }

    public static Sex getSex(int code) {
        for (Sex type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        return DEFAULT;
    }

    public int getCode() {
        return this.code;
    }
}
