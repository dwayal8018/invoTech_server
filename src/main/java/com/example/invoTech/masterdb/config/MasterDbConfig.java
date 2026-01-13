package com.example.invoTech.masterdb.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class MasterDbConfig {

    @Bean(name = "masterDataSource")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/inventory_master?createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true&useSSL=false")
                .username("root")
                .password("root")
                .build();
    }

    @Bean(name = "masterJdbcTemplate")
    public JdbcTemplate masterJdbcTemplate(DataSource masterDataSource) {
        return new JdbcTemplate(masterDataSource);
    }
}
