package com.example.invoTech.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class MultiTenantConfig {

	

	@Bean
	public DataSource tenantDataSource() {
		MultiTenantDataSource multiTenantDataSource = new MultiTenantDataSource();

		// Start with empty target data sources - they will be loaded lazily when first needed
		Map<Object, Object> targetDataSources = new HashMap<>();
		multiTenantDataSource.setTargetDataSources(targetDataSources);

		// Always set a default target data source - use master DB
		HikariDataSource defaultDS = new HikariDataSource();
		defaultDS.setDriverClassName("com.mysql.cj.jdbc.Driver");
		defaultDS.setJdbcUrl("jdbc:mysql://localhost:3306/inventory_master?createDatabaseIfNotExist=true");
		defaultDS.setUsername("root");
		defaultDS.setPassword("root");
		multiTenantDataSource.setDefaultTargetDataSource(defaultDS);

		return multiTenantDataSource;
	}

	

}
