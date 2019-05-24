package com.github.oahnus.scaffold.common.interfaces;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by oahnus on 2017/10/1
 * 21:11.
 */
public interface HttpMixin {
    default HttpServletRequest request() {
        // todo
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
    default HttpServletResponse response() {
        // todo
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    default Object getRequestAttr(String attrName) {
        return request().getAttribute(attrName);
    }

    default Object getSessionAttr(String attrName) {
        return request().getSession().getAttribute(attrName);
    }

    default String getToken() {
        return request().getHeader("TOKEN");
    }

    default HttpSession getSession() {
        return request().getSession();
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
