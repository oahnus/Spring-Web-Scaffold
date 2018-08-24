package top.oahnus.common.enums;

/**
 * Created by oahnus on 2018/8/24
 * 14:18.
 */
public enum ESex {
    DEFAULT(0),
    FEMALE(1),
    MALE(2);

    private int code;

    ESex(int code) {
        this.code = code;
    }

    public static ESex getESex(int code) {
        for (ESex type : values()) {
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
