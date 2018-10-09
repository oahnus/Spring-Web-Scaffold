package com.github.oahnus.scaffold.auth.server.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by oahnus on 2018/9/30
 * 11:08.
 */
@Data
@Table(name = "client_service")
public class ClientService {
    @Id
    private String id;
    private String clientId;
    private String serviceId;
    private Date crtTime;
    private Date uptTime;
    private String crtHost;
}
