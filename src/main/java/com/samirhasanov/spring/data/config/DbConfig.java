/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samirhasanov.spring.data.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Hasanov (Asus)
 */
@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class DbConfig {
    
    @Value("${database.postgresql.driver}")
    private String databaseDriver;
    
    @Value("${database.postgresql.url}")
    private String databaseUrl;
    
    @Value("${database.postgresql.username}")
    private String databaseUsername;
    
    @Value("${database.postgresql.password}")
    private String databasePassword;
    
    @Bean // get values from property file
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
    @Bean // translate JPA exceptions to Spring Data exceptions (DataAccessException)
    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
    
    @Bean // transaction manager for Hibernate
    public PlatformTransactionManager platformTransactionManager() {
        return new HibernateTransactionManager(sessionFactory());
    }
    
    @Bean // Hibernate specific properties
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.format_sql", true);
        properties.put("hibernate.use_sql_comments", true);
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        
        return properties;
    }
    
    @Bean // Session factory for Hibernate
    public SessionFactory sessionFactory() {
        return new LocalSessionFactoryBuilder(dataSource())
                .scanPackages("com.samirhasanov.spring.data.entity")
                .addProperties(hibernateProperties())
                .buildSessionFactory();
    }
    
    @Lazy
    @Bean(destroyMethod = "close") // datasource for database connections -_- məzələnmə
    public DataSource dataSource() {
        try {
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setDriverClassName(databaseDriver);
            hikariConfig.setJdbcUrl(databaseUrl);
            hikariConfig.setUsername(databaseUsername);
            hikariConfig.setPassword(databasePassword);
            
            // Connection pool specific configuration
            hikariConfig.setMaximumPoolSize(10);
            hikariConfig.setConnectionTestQuery("select 1");
            hikariConfig.setPoolName("springHikariCP");
            hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
            hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
            hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "2048");
            hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");
            
            HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
            
            return hikariDataSource;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
