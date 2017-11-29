package top.oahnus.service.session;

import top.oahnus.interfaces.LoginMixin;

/**
 * Created by oahnus on 2017/11/29
 * 12:00.
 */
public interface SessionService extends LoginMixin {
    int EXPIRE = 7;
    String KEY_BASE = "SCAFFOLD:";

    Long saveToken(Long userId, String token);
    Long getUserId(String token);
}
