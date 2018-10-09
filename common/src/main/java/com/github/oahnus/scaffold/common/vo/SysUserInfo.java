package com.github.oahnus.scaffold.common.vo;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by oahnus on 2018/9/30
 * 9:56.
 */
@Data
public class SysUserInfo implements Serializable {
    private String id;
    private String username;
    private String pwdHash;
    private String salt;

    private Date lastPasswordReset;

    private Boolean isEnable = true;
}
