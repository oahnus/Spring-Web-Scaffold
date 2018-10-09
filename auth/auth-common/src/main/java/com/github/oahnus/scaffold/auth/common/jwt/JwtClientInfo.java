package com.github.oahnus.scaffold.auth.common.jwt;

import lombok.Data;


/**
 * Created by oahnus on 2018/9/30
 * 11:23.
 */
@Data
public class JwtClientInfo extends JwtInfo {
    private Long expire;
    private String source;
    private String clientId;

    public JwtClientInfo() {}

    public JwtClientInfo(String clientId, String id, String name) {
        this.clientId = clientId;
        this.name = name;
        this.id = id;

        this.username = this.clientId;
    }
}
