package com.github.oahnus.scaffold.auth.server.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by oahnus on 2018/9/30
 * 10:58.
 */
@Configuration
@Data
public class ClientConfiguration {
    @Value("${auth.client.id}")
    private String clientId;
    @Value("${auth.client.token-header}")
    private String clientTokenHeader;
    @Value("${auth.client.secret}")
    private String clientSecret;
}
