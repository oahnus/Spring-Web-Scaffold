package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.oahnus.interfaces.LoginMixin;

/**
 * Created by oahnus on 2017/10/3
 * 0:56.
 */
@Service
@CacheConfig(cacheNames = "token")
public class SessionService implements LoginMixin {
    public static Integer expire = 7;

    private final String TOKEN = "token";

    @CachePut(value = "token", key = "#token", condition = "#userId != null")
    public Long saveToken(Long userId, String token) {
        System.out.println(token);
        return userId;
    }

    @Cacheable(value="token", key = "#token")
    public Long getUserId(String token) {
        return null;
    }

    public Long getSessionUserId() {
        return getUserId(getToken());
    }
}
