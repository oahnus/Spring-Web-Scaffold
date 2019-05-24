package com.github.oahnus.scaffold.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 0:mobile web 1:微信公众号 2:pc web 3:微信小程序 
     */
    private Integer source;

    /**
     * 分享人id
     */
    @Column(name = "share_id")
    private Long shareId;

    /**
     * 积分值
     */
    private Integer credit;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", nickname=").append(nickname);
        sb.append(", source=").append(source);
        sb.append(", shareId=").append(shareId);
        sb.append(", credit=").append(credit);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}