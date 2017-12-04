package top.oahnus.common.config.druid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Map;

/**
 * Created by oahnus on 2017/11/28
 * @EnableTransactionManagement(proxyTargetClass = true)
 * 当不指定 proxyTargetClass = true 时，启动会抛异常 [ ...because it is a JDK dynamic proxy that implement... ]
 * 加了@Transaction的类会自动开启动态代理，因其使用了JDK动态代理而抛出异常
 * 9:42.
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories(
        entityManagerFactoryRef = "primaryEntityManagerFactory",
        transactionManagerRef = "primaryTransactionManager",
        basePackages = {"top.oahnus.repository.primary"}
)
public class PrimaryDataSourceConfig {

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    @Qualifier("primaryDataSource")
    private DataSource primaryDataSource;

    /**
     * 我们通过LocalContainerEntityManagerFactoryBean来获取EntityManagerFactory实例
     * LocalContainerEntityManagerFactoryBean和userEntityManagerFactory方法其中一个注解@Primary即可
     * @return
     */
    @Bean(name = "primaryEntityManagerFactoryBean")
    //@Primary
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(primaryDataSource)
                .properties(getVendorProperties(primaryDataSource))
                .packages("top.oahnus.domain.primary") //设置实体类所在位置
                .persistenceUnit("primary")
                .build();
        //.getObject();//不要在这里直接获取EntityManagerFactory
    }

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

    /**
     * EntityManagerFactory类似于Hibernate的SessionFactory,mybatis的SqlSessionFactory
     * 总之,在执行操作之前,我们总要获取一个EntityManager,这就类似于Hibernate的Session,
     * mybatis的sqlSession.
     * @param builder
     * @return
     */
    @Bean(name = "primaryEntityManagerFactory")
    @Primary
    public EntityManagerFactory primaryEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return this.primaryEntityManagerFactoryBean(builder).getObject();
    }

    /**
     * 配置事物管理器
     * @return
     */
    @Bean(name = "primaryTransactionManager")
    @Primary
    public PlatformTransactionManager writeTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(primaryEntityManagerFactory(builder));
    }
}
