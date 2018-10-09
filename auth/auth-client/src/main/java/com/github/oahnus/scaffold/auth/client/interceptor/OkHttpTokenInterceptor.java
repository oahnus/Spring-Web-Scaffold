package com.github.oahnus.scaffold.auth.client.interceptor;

import com.github.oahnus.scaffold.auth.client.config.ServiceAuthConfig;
import com.github.oahnus.scaffold.auth.client.config.SysUserAuthConfig;
import com.github.oahnus.scaffold.auth.client.jwt.ServiceAuthUtil;
import com.github.oahnus.scaffold.common.constants.Constants;
import com.github.oahnus.scaffold.common.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class OkHttpTokenInterceptor implements Interceptor {
    @Autowired
    @Lazy
    private ServiceAuthUtil serviceAuthUtil;
    @Autowired
    @Lazy
    private ServiceAuthConfig serviceAuthConfig;
    @Autowired
    @Lazy
    private SysUserAuthConfig sysUserAuthConfig;

    @Override
    public Response intercept(Chain chain) throws IOException {
        log.debug("[OkHttpTokenInterceptor].intercept - Enter");
        Request newRequest = null;
        if (chain.request().url().toString().contains("client/token")) {
            newRequest = chain.request()
                    .newBuilder()
                    .header(sysUserAuthConfig.getTokenHeader(), RequestContext.TOKEN())
                    .build();
        } else {
            newRequest = chain.request()
                    .newBuilder()
                    .header(sysUserAuthConfig.getTokenHeader(), RequestContext.TOKEN())
                    .header(serviceAuthConfig.getTokenHeader(), serviceAuthUtil.getClientToken())
                    .build();
        }
        Response response = chain.proceed(newRequest);
        if (HttpStatus.FORBIDDEN.value() == response.code()) {
            if (response.body().string().contains(String.valueOf(Constants.EX_CLIENT_INVALID_CODE))) {
                log.info("Client Token Expire,Retry to request...");
                serviceAuthUtil.refreshClientToken();

                newRequest = chain.request()
                        .newBuilder()
                        .header(sysUserAuthConfig.getTokenHeader(), RequestContext.TOKEN())
                        .header(serviceAuthConfig.getTokenHeader(), serviceAuthUtil.getClientToken())
                        .build();
                response = chain.proceed(newRequest);
            }
        }
        return response;
    }
}
