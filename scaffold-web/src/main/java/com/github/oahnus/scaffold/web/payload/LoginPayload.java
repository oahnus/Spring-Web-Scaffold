package com.github.oahnus.scaffold.web.payload;

import lombok.Data;

/**
 * Created by oahnus on 2019/5/8
 * 14:08.
 */
@Data
public class LoginPayload {
    private String username;
    private String password;
}
