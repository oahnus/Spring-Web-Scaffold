package top.oahnus.interfaces;

import top.oahnus.common.config.RedisDao;
import top.oahnus.common.constants.Constants;
import top.oahnus.common.constants.Message;
import top.oahnus.common.exception.AuthException;

/**
 * Created by oahnus on 2017/10/3
 * 9:34.
 */
public interface SingleLoginMixin extends LoginMixin {
    String TOKEN_KEY = "token";

    RedisDao redis();

    default Long getUserId() {
        return Long.parseLong(request().getHeader(Constants.USER_ID));
    }

    default String getSessionToken() {
        Long userId = getUserId();
        if (userId == null) {
            throw new AuthException(Message.NO_AUTH);
        }
        String token = redis().getBean(getKey(TOKEN_KEY, userId), String.class);
        if (token == null) {
            throw new AuthException(Message.NO_AUTH);
        }
        return token;
    }

    default void saveToken(Long userId, String token) {
        redis().putBean(getKey(TOKEN_KEY, userId), token, String.class, getTimeout());
    }

    default Integer getTimeout(){
        return 7*60*60*24;
    }
}
