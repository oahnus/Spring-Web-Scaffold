package top.oahnus.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import top.oahnus.common.utils.PasswordHash;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by oahnus on 2018/8/20
 * 11:09.
 */
public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        try {
            return PasswordHash.createHash(rawPassword.toString());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        try {
            return PasswordHash.validatePassword(rawPassword.toString(), encodedPassword);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return false;
    }
}
