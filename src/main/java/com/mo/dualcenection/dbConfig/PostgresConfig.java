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
        basePackages = "com.mo.dualcenection.respository.jpa.postgres",
        entityManagerFactoryRef = "posgrestEntityManager",
        transactionManagerRef = "posgrestTransactionManager"
)
public class PostgresConfig {
    @Value("${ms-config.postgres.username}")
    private String username;
    @Value("${ms-config.postgres.password}")
    private String password;
    @Value("${ms-config.postgres.driver-class}")
    private String driverClass;
    @Value("${ms-config.postgres.hibernate-dialect}")
    private String hibernateDialect;

    @Value("${ms-config.postgres.url}")
    private String ulrHealthmanager;
    @Value("${ms-config.postgres.hikari.maximumPoolSize}")
    private Integer maximumPoolSize;
    @Value("${ms-config.postgres.hikari.minimumIdle}")
    private Integer minimumIdle;
    @Value("${ms-config.postgres.hikari.maxLifetime}")
    private Integer maxLifetime;
    @Value("${ms-config.postgres.hikari.autoCommit}")
    private Boolean autoCommit;

    private final String ENTITY_MANAGER_NAME = "posgrestEntityManager";
    private final String ENTITY_BASE_PACKAGE = "com.mo.dualcenection.respository.entity.postgres";
    private final String HIKARI_POOL_NAME = "HikariPool - posgres";
    private final String HIBERNATE_DIALECT_KEY = "hibernate.dialect";
    private final Integer CONNECTION_TIME_OUT = 300000;
    private final Integer IDLE_TIME_OUT = 1800000;
    private final Integer LEAK_DETECTION_THRESHOLD = 300000;




    @Bean
    public DataSource datasourcePostgres() {
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
    public LocalContainerEntityManagerFactoryBean posgrestEntityManager() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
                = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setPersistenceUnitName(ENTITY_MANAGER_NAME);
        entityManagerFactoryBean.setDataSource(datasourcePostgres());
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
    public PlatformTransactionManager posgrestTransactionManager() {

        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                posgrestEntityManager().getObject());
        return transactionManager;
    }
}
