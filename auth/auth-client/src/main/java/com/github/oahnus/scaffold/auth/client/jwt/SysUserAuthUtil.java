package com.github.oahnus.scaffold.auth.client.jwt;

import com.github.oahnus.scaffold.auth.client.config.SysUserAuthConfig;
import com.github.oahnus.scaffold.auth.common.jwt.JwtInfo;
import com.github.oahnus.scaffold.auth.common.jwt.JwtUtils;
import com.github.oahnus.scaffold.common.exception.ClientException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ace on 2017/9/15.
 */
@Configuration
public class SysUserAuthUtil {
    @Autowired
    private SysUserAuthConfig sysUserAuthConfig;

    public JwtInfo getInfoFromToken(String token) throws Exception {
        try {
            return JwtUtils.getJwtUserInfoFromToken(token);
        } catch (ExpiredJwtException ex) {
            throw new ClientException("User token expired!");
        } catch (SignatureException ex) {
            throw new ClientException("User token signature error!");
        } catch (IllegalArgumentException ex) {
            throw new ClientException("User token is null or empty!");
        }
    }
}
