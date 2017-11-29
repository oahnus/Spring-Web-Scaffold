package top.oahnus.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by oahnus on 2017/11/29
 * 11:07.
 */
public interface LoginStrategy {
    boolean verifyRequest(HttpServletRequest request, HttpServletResponse response, Object handler);
}
