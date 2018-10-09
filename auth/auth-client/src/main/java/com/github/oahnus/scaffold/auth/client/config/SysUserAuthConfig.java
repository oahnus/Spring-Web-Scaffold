package com.github.oahnus.scaffold.auth.client.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by oahnus on 2018/10/1
 * 23:11.
 */
@Data
@Component
public class SysUserAuthConfig {
    @Value("${auth.user.token-header}")
    private String tokenHeader;

    private byte[] pubKeyByte;
}
