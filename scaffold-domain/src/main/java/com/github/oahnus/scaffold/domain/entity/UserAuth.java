package com.github.oahnus.scaffold.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Table(name = "user_auth")
public class UserAuth implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * user id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    private String salt;

    /**
     * 微信openId
     */
    @Column(name = "open_id")
    private String openId;

    /**
     * 用户状态 0 冻结禁止登录 1 允许登录
     */
    private Byte state;

    /**
     * 创建日期
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新日期
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 软删除标志
     */
    @Column(name = "is_delete")
    private Boolean isDelete;

    private static final long serialVersionUID = 1L;

    @Transient
    private Set<String> roles = new HashSet<>();
    @Transient
    private Set<String> perms = new HashSet<>();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", openId=").append(openId);
        sb.append(", state=").append(state);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}