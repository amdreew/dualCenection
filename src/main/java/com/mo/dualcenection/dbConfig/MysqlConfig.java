package com.mo.dualcenection.dbConfig;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.mo.dualcenection.respository.jpa.mysql",
        entityManagerFactoryRef = "mysqlEntityManager",
        transactionManagerRef = "mysqlTransactionManager"
)
public class MysqlConfig {
    @Value("${ms-config.mysql.username}")
    private String username;
    @Value("${ms-config.mysql.password}")
    private String password;
    @Value("${ms-config.mysql.driver-class}")
    private String driverClass;
    @Value("${ms-config.mysql.hibernate-dialect}")
    private String hibernateDialect;

    @Value("${ms-config.mysql.url}")
    private String ulrHealthmanager;
    @Value("${ms-config.mysql.hikari.maximumPoolSize}")
    private Integer maximumPoolSize;
    @Value("${ms-config.mysql.hikari.minimumIdle}")
    private Integer minimumIdle;
    @Value("${ms-config.mysql.hikari.maxLifetime}")
    private Integer maxLifetime;
    @Value("${ms-config.mysql.hikari.autoCommit}")
    private Boolean autoCommit;

    private final String ENTITY_MANAGER_NAME = "mysqlEntityManager";
    private final String ENTITY_BASE_PACKAGE = "com.mo.dualcenection.respository.entity.mysql";
    private final String HIKARI_POOL_NAME = "HikariPool - mysql";
    private final String HIBERNATE_DIALECT_KEY = "hibernate.dialect";
    private final Integer CONNECTION_TIME_OUT = 300000;
    private final Integer IDLE_TIME_OUT = 1800000;
    private final Integer LEAK_DETECTION_THRESHOLD = 300000;




    @Bean
    public DataSource datasourcemysql() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driverClass);
        hikariConfig.setJdbcUrl(ulrHealthmanager);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setMaximumPoolSize(maximumPoolSize);
        hikariConfig.setMinimumIdle(minimumIdle);
        hikariConfig.setMaxLifetime(maxLifetime);
        hikariConfig.setAutoCommit(autoCommit);
        hikariConfig.setPoolName(HIKARI_POOL_NAME);
        hikariConfig.setConnectionTimeout(CONNECTION_TIME_OUT);
        hikariConfig.setIdleTimeout(IDLE_TIME_OUT);
        hikariConfig.setLeakDetectionThreshold(LEAK_DETECTION_THRESHOLD);

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        return dataSource;
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean mysqlEntityManager() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
                = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setPersistenceUnitName(ENTITY_MANAGER_NAME);
        entityManagerFactoryBean.setDataSource(datasourcemysql());
        entityManagerFactoryBean.setPackagesToScan(ENTITY_BASE_PACKAGE);
        HibernateJpaVendorAdapter vendorAdapter
                = new HibernateJpaVendorAdapter();
        Map<String, Object> properties = new HashMap<>();
        properties.put(HIBERNATE_DIALECT_KEY, hibernateDialect);
        entityManagerFactoryBean.setJpaPropertyMap(properties);
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        return entityManagerFactoryBean;
    }

    @Primary
    @Bean
    public PlatformTransactionManager mysqlTransactionManager() {

        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                mysqlEntityManager().getObject());
        return transactionManager;
    }
}
