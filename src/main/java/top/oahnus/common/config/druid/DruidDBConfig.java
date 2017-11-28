package top.oahnus.common.config.druid;

import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import top.oahnus.interfaces.LoggerMixin;

/**
 * Created by oahnus on 2017/11/24
 * 15:04.
 */
@Configuration
public class DruidDBConfig implements LoggerMixin {
    @Bean(name="wallConfig")
    WallConfig wallFilterConfig() {
        WallConfig wc = new WallConfig();
        wc.setMultiStatementAllow(true);
        return wc;
    }

    @Bean(name = "wallFilter")
    @DependsOn("wallConfig")
    WallFilter wallFilter(WallConfig wallConfig){
        WallFilter filter = new WallFilter ();
        filter.setConfig(wallConfig);
        return filter;
    }
}
