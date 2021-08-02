package com.jktoy.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DBConfig {

    @Bean(name = "jkDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource jkDataSource() throws SQLException {
        HikariDataSource hikariDataSource = new HikariDataSource();
        return hikariDataSource;
    }
}
