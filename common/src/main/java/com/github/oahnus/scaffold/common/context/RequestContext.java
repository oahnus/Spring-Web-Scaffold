package com.github.oahnus.scaffold.common.context;

import static com.github.oahnus.scaffold.common.constants.Constants.*;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by oahnus on 2018/9/6
 * 19:13.
 */
@Data
public class RequestContext {

    public static final Integer CONTEXT_VARIABLE_MAX_SIZE = 10;

    public static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    public static void set(String key, Object value) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>(CONTEXT_VARIABLE_MAX_SIZE);
            threadLocal.set(map);
        }
        map.put(key, value);
    }

    public static Object get(String key) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>(CONTEXT_VARIABLE_MAX_SIZE);
            threadLocal.set(map);
            return null;
        }
        return map.get(key);
    }

    public static String TOKEN() {
        return get(CONTEXT_TOKEN).toString();
    }

    public static String USERNAME() {
        return get(CONTEXT_USERNAME).toString();
    }

    public static String NAME() {
        return get(CONTEXT_NAME).toString();
    }

    public static void setUserId(String userId) {
        set(CONTEXT_USER_ID, userId);
    }

    public static void setToken(String token) {
        set(CONTEXT_TOKEN, token);
    }

    public static void setUsername(String username) {
        set(CONTEXT_USERNAME, username);
    }

    public static void setName(String name) {
        set(CONTEXT_NAME, name);
    }

    public static void clear() {
        Map<String, Object> map = threadLocal.get();
        if (map != null) {
            map.clear();
        }
    }
}
