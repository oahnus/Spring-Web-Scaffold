package top.oahnus.common.config.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oahnus on 2018/8/8
 * 13:44.
 */
@Configuration
public class MongoConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.data.mongodb.custom")
    MongoDBProp mongoDBProp() {
        return new MongoDBProp();
    }

    @Bean
    MongoDbFactory mongoDbFactory() {
        //客户端配置（连接数、副本集群验证）
        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        builder.connectionsPerHost(mongoDBProp().getConnectionsPerHost());
        builder.minConnectionsPerHost(mongoDBProp().getMinConnectionsPerHost());
        if (mongoDBProp().getReplicaSet() != null) {
            builder.requiredReplicaSetName(mongoDBProp().getReplicaSet());
        }
        MongoClientOptions mongoClientOptions = builder.build();

        // MongoDB地址列表
        List<ServerAddress> serverAddresses = new ArrayList<>();
        for (String host : mongoDBProp().getHosts()) {
            Integer index = mongoDBProp().getHosts().indexOf(host);
            Integer port = mongoDBProp().getPorts().get(index);

            ServerAddress serverAddress = new ServerAddress(host, port);
            serverAddresses.add(serverAddress);
        }
        System.out.println("serverAddresses:" + serverAddresses.toString());

        // 连接认证
        List<MongoCredential> mongoCredentialList = new ArrayList<>();
        if (mongoDBProp().getUsername() != null) {
            mongoCredentialList.add(MongoCredential.createScramSha1Credential(
                    mongoDBProp().getUsername(),
                    mongoDBProp().getAuthenticationDatabase() != null ? mongoDBProp().getAuthenticationDatabase() : mongoDBProp().getDatabase(),
                    mongoDBProp().getPassword().toCharArray()));
        }
        System.out.println("mongoCredentialList:" + mongoCredentialList.toString());

        //创建客户端和Factory
        MongoClient mongoClient = new MongoClient(serverAddresses, mongoCredentialList, mongoClientOptions);
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClient, mongoDBProp().getDatabase());

        return mongoDbFactory;
    }
}
