package top.oahnus.interfaces;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by oahnus on 2017/10/1
 * 21:11.
 */
public interface HttpMixin {
    default HttpServletRequest request() {
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
