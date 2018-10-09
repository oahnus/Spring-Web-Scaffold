package com.github.oahnus.scaffold.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.oahnus.scaffold.auth.client.config.ServiceAuthConfig;
import com.github.oahnus.scaffold.auth.client.config.SysUserAuthConfig;
import com.github.oahnus.scaffold.auth.client.jwt.ServiceAuthUtil;
import com.github.oahnus.scaffold.auth.client.jwt.SysUserAuthUtil;
import com.github.oahnus.scaffold.common.dto.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * Created by oahnus on 2018/9/30
 * 16:05.
 */
@Slf4j
@Configuration
public class AccessFilter implements GlobalFilter {

    @Autowired
    SysUserAuthUtil sysUserAuthUtil;
    @Autowired
    SysUserAuthConfig sysUserAuthConfig;
    @Autowired
    ServiceAuthConfig serviceAuthConfig;
    @Autowired
    ServiceAuthUtil serviceAuthUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("[Gateway] - filter: 过滤");

        ServerHttpRequest request = exchange.getRequest();
        String requestUri = request.getPath().pathWithinApplication().value();
        ServerHttpRequest.Builder mutate = request.mutate();

        log.info("[AccessFilter].filter - requestUri = {}", requestUri);
        mutate.header(serviceAuthConfig.getTokenHeader(), serviceAuthUtil.getClientToken());
        ServerHttpRequest build = mutate.build();
        return chain.filter(exchange.mutate().request(build).build());
    }

    private Mono<Void> getVoidMono(ServerWebExchange serverWebExchange, ResponseData body) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.OK);
        ObjectMapper mapper = new ObjectMapper();
        byte[] bytes = JSONObject.toJSONString(body).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = serverWebExchange.getResponse().bufferFactory().wrap(bytes);
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }
}
