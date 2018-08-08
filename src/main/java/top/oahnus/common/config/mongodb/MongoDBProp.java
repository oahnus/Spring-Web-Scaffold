package top.oahnus.common.config.mongodb;

import lombok.Data;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by oahnus on 2018/8/8
 * 12:53.
 */
@Data
public class MongoDBProp {

    private String database;

    private List<String> hosts;

    private List<Integer> ports;

    private String replicaSet;
    private String username;
    private String password;
    private String authenticationDatabase;
    private Integer minConnectionsPerHost = 10;
    private Integer connectionsPerHost = 1;
}
