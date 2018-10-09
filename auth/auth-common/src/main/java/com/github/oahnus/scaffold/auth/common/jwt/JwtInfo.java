package com.github.oahnus.scaffold.auth.common.jwt;

import lombok.Data;

import java.util.Date;

/**
 * Created by oahnus on 2018/9/30
 * 11:22.
 */
@Data
public abstract class JwtInfo {
    protected String username;
    protected String id;
    protected String name;

    protected Date crtTime;
    protected Long expire;
}
