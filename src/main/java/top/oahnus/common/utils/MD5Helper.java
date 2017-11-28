package top.oahnus.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by oahnus on 2017/10/7
 * 22:24.
 */
public class MD5Helper {
    private static Logger LOGGER = LoggerFactory.getLogger(MD5Helper.class);

    private static final String slat = "jSeI32!f;%ir(Ms23";

    public static String getMd5(String clearText) {
        try {
            int length = clearText.length();
            String preText = clearText.substring(0, length / 2) + slat + clearText.substring(length / 2);
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bytes = messageDigest.digest(preText.getBytes("utf-8"));
            return toCipherText(bytes);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            LOGGER.error("ERROR: {}", e.getMessage());
            return "";
        }
    }

    private static String toCipherText(byte[] bytes) {
        StringBuilder cipherText = new StringBuilder();
        for (byte aByte : bytes) {
            int val = aByte & 0xff;
            if (val < 16) {
                cipherText.append("0");
            }
            cipherText.append(Integer.toHexString(val));
        }
        return cipherText.toString();
    }

    public static void main(String... args) {
        String cipher = getMd5("123456");
        System.out.println("Cipher:" + cipher);
    }
}
