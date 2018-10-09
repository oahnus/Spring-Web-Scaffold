package com.github.oahnus.scaffold.auth.common.jwt;

import lombok.Data;

/**
 * Created by oahnus on 2018/9/30
 * 11:23.
 */
@Data
public class JwtUserInfo extends JwtInfo {
    public JwtUserInfo() { }

    public JwtUserInfo(String id, String username, String name) {
        this.id = id;
        this.username = username;
        this.name = name;
    }
}
