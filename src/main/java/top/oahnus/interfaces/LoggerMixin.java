package top.oahnus.interfaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by oahnus on 2017/10/3
 * 13:11.
 */
public interface LoggerMixin {
    default Logger logger() {
        return LoggerFactory.getLogger(getClass());
    }
}
