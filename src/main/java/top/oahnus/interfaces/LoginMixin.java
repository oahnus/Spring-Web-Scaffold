package top.oahnus.interfaces;

import top.oahnus.common.constants.Constants;

/**
 * Created by oahnus on 2017/10/1
 * 21:24.
 */
public interface LoginMixin extends HttpMixin {
    default String getToken() {
        return request().getHeader(Constants.TOKEN);
    }
}
