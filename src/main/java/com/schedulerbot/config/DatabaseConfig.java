package com.schedulerbot.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class DatabaseConfig {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Bean
    public Connection connection() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        try {
            return new HikariDataSource(config).getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());

        }
    }
}
