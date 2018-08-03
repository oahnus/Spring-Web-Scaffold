package top.oahnus.common.config.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;
import top.oahnus.common.config.mybatis.intercepror.MyMybatisInterceptor;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by oahnus on 2017/11/28
 * @EnableTransactionManagement(proxyTargetClass = true)
 * 当不指定 proxyTargetClass = true 时，启动会抛异常 [ ...because it is a JDK dynamic proxy that implement... ]
 * 加了@Transaction的类会自动开启动态代理，因其使用了JDK动态代理而抛出异常
 * 9:42.
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(basePackages = PrimaryDataSourceConfig.PACKAGE, sqlSessionFactoryRef = PrimaryDataSourceConfig.SQL_SESSION_FACTORY)
public class PrimaryDataSourceConfig {

    public static final String SIGN = "primary";
    public static final String DATASOURCE = SIGN + "DataSource";
    public static final String TRANSACTION_MANAGER = SIGN + "TransactionManager";
    public static final String SQL_SESSION_FACTORY = SIGN + "SqlSessionFactory";
    public static final String PACKAGE = "top.oahnus.mapper." + SIGN;
    public static final String MAPPER_LOCATION = "classpath:mapper/"+SIGN+"/**.xml";

    @Bean(name = "primaryDataSource")
    @Primary
    @Qualifier("primaryDateSource")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource() {
        return new DruidDataSource();
    }

    @Primary
    @Bean(name = TRANSACTION_MANAGER)
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(primaryDataSource());
    }

    @Primary
    @Bean(name = SQL_SESSION_FACTORY)
    public SqlSessionFactory sqlSessionFactory(@Qualifier(DATASOURCE) DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        sessionFactory.setPlugins(new Interceptor[]{
                myMybatisInterceptor(),
        });
        return sessionFactory.getObject();
    }

    @Bean
    public MyMybatisInterceptor myMybatisInterceptor() {
        return new MyMybatisInterceptor();
    }
}
