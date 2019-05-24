package com.github.oahnus.scaffold.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Table(name = "sys_log")
public class SysLog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 请求id
     */
    private String ip;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 请求参数
     */
    private String params;

    /**
     * 请求方法
     */
    @Column(name = "request_method")
    private String requestMethod;

    /**
     * handle方法名
     */
    @Column(name = "method_name")
    private String methodName;

    /**
     * 操作人
     */
    @Column(name = "user_id")
    private Long userId;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ip=").append(ip);
        sb.append(", createTime=").append(createTime);
        sb.append(", params=").append(params);
        sb.append(", requestMethod=").append(requestMethod);
        sb.append(", methodName=").append(methodName);
        sb.append(", userId=").append(userId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}