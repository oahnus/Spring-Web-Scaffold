package top.oahnus.common.utils;

/**
 * Created by oahnus on 2017/10/7
 * 22:01.
 */
public class StringHelper {
    public static Boolean isBlank(String str) {
        return str == null || str.equals("") || str.trim().equals("");
    }
}
