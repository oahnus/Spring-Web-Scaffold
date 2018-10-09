
package com.github.oahnus.scaffold.auth.client.jwt;

import com.github.oahnus.scaffold.auth.client.config.ServiceAuthConfig;
import com.github.oahnus.scaffold.auth.client.feign.ServiceAuthFeign;
import com.github.oahnus.scaffold.auth.common.jwt.JwtInfo;
import com.github.oahnus.scaffold.auth.common.jwt.JwtUtils;
import com.github.oahnus.scaffold.common.dto.ResponseData;
import com.github.oahnus.scaffold.common.exception.ClientException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@Slf4j
@EnableScheduling
public class ServiceAuthUtil {
    @Autowired
    private ServiceAuthConfig serviceAuthConfig;

    @Autowired
    private ServiceAuthFeign serviceAuthFeign;

    private List<String> allowedClients;
    private String clientToken;


    public JwtInfo getInfoFromToken(String token) {
        try {
            return JwtUtils.getJwtUserInfoFromToken(token);
        } catch (ExpiredJwtException ex) {
            throw new ClientException("Client token expired!");
        } catch (SignatureException ex) {
            throw new ClientException("Client token signature error!");
        } catch (IllegalArgumentException ex) {
            throw new ClientException("Client token is null or empty!");
        }
    }

    @Scheduled(cron = "0/30 * * * * ?")
    public void refreshAllowedClient() {
        log.info("refresh allowedClients.....");
        ResponseData<List<String>> resp = serviceAuthFeign.getAllowedClient(serviceAuthConfig.getClientId(), serviceAuthConfig.getClientSecret());
        if (resp.getCode() == 0) {
            this.allowedClients = resp.getData();
        }
    }

    @Scheduled(cron = "0 0/30 * * * ?")
    public void refreshClientToken() {
        log.info("refresh client token.....");
        ResponseData resp = serviceAuthFeign.getAccessToken(serviceAuthConfig.getClientId(), serviceAuthConfig.getClientSecret());
        if (resp.getCode() == 0) {
            ResponseData<String> clientToken = (ResponseData<String>) resp;
            this.clientToken = clientToken.getData();
            log.info("[ServiceAuthUtil].refreshClientToken - token = {}", this.clientToken);
        }
    }


    public String getClientToken() {
        if (this.clientToken == null) {
            this.refreshClientToken();
        }
        return clientToken;
    }

    public List<String> getAllowedClients() {
        if (this.allowedClients == null) {
            this.refreshAllowedClient();
        }
        return allowedClients;
    }
}