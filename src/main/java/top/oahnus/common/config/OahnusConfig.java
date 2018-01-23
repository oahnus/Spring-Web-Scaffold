package top.oahnus.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oahnus on 2018/1/20
 * 17:13.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "oahnus.config")
public class OahnusConfig {
    private volatile String name;

    private List<Long> couponIds = new ArrayList<>();
    @Value("#{'${oahnus.config.coupons}'.split(',')}")
    private List<Long> coupons = new ArrayList<>();
}
