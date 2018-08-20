package top.oahnus.common.enums;

/**
 * Created by oahnus on 2018/8/20
 * 10:12.
 */
public enum Device {
    AUDIENCE_UNKNOWN("unknown"),
    AUDIENCE_WEB    ("web"),
    AUDIENCE_MOBILE ("mobile"),
    AUDIENCE_TABLET ("tablet");

    private String name;

    Device(String name) {
        this.name = name;
    }

    public static Device getDevice(String name) {
        for (Device device: values()) {
            if (device.name.equals(name)) {
                return device;
            }
        }
        return AUDIENCE_UNKNOWN;
    }

    public String getName() {
        return this.name;
    }
}
