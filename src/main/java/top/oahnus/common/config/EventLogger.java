package top.oahnus.common.config;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import top.oahnus.interfaces.LoggerMixin;

/**
 * Created by oahnus on 2017/10/17
 * 21:33.
 */
public class EventLogger implements CacheEventListener<Object, Object>, LoggerMixin {

    @Override
    public void onEvent(CacheEvent<? extends Object, ? extends Object> event) {

        logger().info("Event: " + event.getType() + " Key: " + event.getKey() + " old value: " + event.getOldValue()
                + " new value: " + event.getNewValue());

    }

}
