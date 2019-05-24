package com.github.oahnus.scaffold.common.service;

/**
 * Created by oahnus on 2019/4/24
 * 14:53.
 */
public interface SessionService {
    void saveToken(Long userId, String token);
    Long getUserId(String token);
}
