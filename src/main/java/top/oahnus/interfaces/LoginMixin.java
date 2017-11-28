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

    default String getKey(Object key,Object userId) {
        return Constants.BASE_KEY+key+userId;
    }

    default String getIP() {
        String ip = request().getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request().getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request().getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request().getRemoteAddr();
        }
        return ip;
    }
}
