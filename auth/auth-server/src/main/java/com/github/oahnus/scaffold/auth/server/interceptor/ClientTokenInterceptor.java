package com.github.oahnus.scaffold.auth.server.interceptor;

import com.github.oahnus.scaffold.auth.server.service.ClientAuthService;
import com.github.oahnus.scaffold.auth.server.config.ClientConfiguration;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by oahnus on 2018/9/30
 * 11:02.
 */
@Slf4j
public class ClientTokenInterceptor implements RequestInterceptor {
    @Autowired
    private ClientConfiguration clientConfiguration;
    @Autowired
    private ClientAuthService clientAuthService;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        try {
            log.info("[ClientTokenInterceptor].apply - requestTemplate = {}", requestTemplate);
            String clientTokenHeader = clientConfiguration.getClientTokenHeader();
            String clientSecret = clientConfiguration.getClientSecret();
            requestTemplate.header(clientTokenHeader, clientAuthService.apply(clientConfiguration.getClientId(), clientSecret));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
