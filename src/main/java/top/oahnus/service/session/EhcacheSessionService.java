package top.oahnus.service.session;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by oahnus on 2017/11/29
 * 12:12.
 */
@Service
@CacheConfig(cacheNames = "token")
public class EhcacheSessionService implements SessionService {

    @CachePut(value = "token", key = "#token", condition = "#userId != null")
    public Long saveToken(Long userId, String token) {
        return userId;
    }

    @Cacheable(value="token", key = "#token", condition = "#token != null")
    public Long getUserId(String token) {
        return -1L;
    }

    public Long getSessionUserId() {
        return getUserId(getToken());
    }
}
