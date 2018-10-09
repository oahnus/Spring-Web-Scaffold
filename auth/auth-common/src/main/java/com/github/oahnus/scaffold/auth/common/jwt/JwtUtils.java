package com.github.oahnus.scaffold.auth.common.jwt;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by oahnus on 2018/9/30
 * 14:27.
 */
@Component
@Slf4j
public class JwtUtils {
    private static final String DEFAULT_SECRET = "jf&23;e8Dafsooe12(2lj#ncaajdisenfakio@aslfx8783";
    private static final long EXPIRATION = 7200L;
    private static final String CHAR_ENCODING = "UTF-8";

    private static final String USER_INFO_KEY = "userInfo";
    private static final String CLIENT_INFO_KEY = "clientInfo";


    public static String generateTokenFromSysUserInfo(JwtUserInfo jwtUserInfo) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(USER_INFO_KEY, JSONObject.toJSONString(jwtUserInfo));
        return generateToken(claims);
    }

    public static JwtInfo getJwtUserInfoFromToken(String token) {
        final Claims claims = getClaimsFromToken(token);
        if (claims == null || !claims.containsKey(USER_INFO_KEY)) {
            return null;
        }
        String json = claims.get(USER_INFO_KEY, String.class);
        JwtUserInfo jwtUserInfo = JSONObject.parseObject(json, JwtUserInfo.class);
        return jwtUserInfo;
    }

    public static String generateTokenFromClientInfo(JwtClientInfo jwtClientInfo) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLIENT_INFO_KEY, JSONObject.toJSONString(jwtClientInfo));
        return generateToken(claims);
    }

    public static JwtClientInfo getClientInfoFromToken(String token) {
        final Claims claims = getClaimsFromToken(token);
        if (claims == null || !claims.containsKey(CLIENT_INFO_KEY)) {
            return null;
        }
        String json = claims.get(CLIENT_INFO_KEY, String.class);
        JwtClientInfo clientInfo = JSONObject.parseObject(json, JwtClientInfo.class);
        return clientInfo;
    }

    private static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(DEFAULT_SECRET.getBytes(CHAR_ENCODING))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private static Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + EXPIRATION * 1000);
    }

    private static String generateToken(Map<String, Object> claims) {
        try {
            // TODO signWith deprecated
            return Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(generateExpirationDate())
                    .signWith(SignatureAlgorithm.HS256, DEFAULT_SECRET.getBytes(CHAR_ENCODING))
                    .compact();
        } catch (UnsupportedEncodingException ex) {
            //didn't want to have this method throw the exception, would rather log it and sign the token like it was before
            log.warn(ex.getMessage());
            return Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(generateExpirationDate())
                    .signWith(SignatureAlgorithm.HS256, DEFAULT_SECRET)
                    .compact();
        }
    }

    public Boolean validateToken(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        Date current = new Date();
        if (expiration.before(current)) {
            return false;
        }
        return true;
    }

    private Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }
}
