package com.example.invoTech.config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MultiTenantConfig {

	

	@Bean
	public DataSource tenantDataSource() {
		MultiTenantDataSource multiTenantDataSource = new MultiTenantDataSource();

		// Create master DB connection manually (not using JPA)
		DataSource masterDS = DataSourceBuilder.create().driverClassName("com.mysql.cj.jdbc.Driver")
				.url("jdbc:mysql://localhost:3306/inventory_master").username("root").password("root").build();

		// Query tenant info manually from master DB (JDBC template)
		Map<Object, Object> targetDataSources = new HashMap<>();

		try (Connection conn = masterDS.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT db_name, db_host, db_user, db_password FROM tenants")) {

			while (rs.next()) {
				String dbName = rs.getString("db_name");
				String dbHost = rs.getString("db_host");
				String dbUser = rs.getString("db_user");
				String dbPass = rs.getString("db_password");

				DataSource tenantDS = DataSourceBuilder.create().driverClassName("com.mysql.cj.jdbc.Driver")
						.url("jdbc:mysql://" + dbHost + ":3306/" + dbName).username(dbUser).password(dbPass).build();

				targetDataSources.put(dbName, tenantDS);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

//		DataSource tenant1 = DataSourceBuilder.create()
//                .driverClassName("com.mysql.cj.jdbc.Driver")
//                .url("jdbc:mysql://localhost:3306/inventory_master")
//                .username("root")
//                .password("root")
//                .build();
//
//        targetDataSources.put("inventory_master", tenant1);

		multiTenantDataSource.setTargetDataSources(targetDataSources);

		if (!targetDataSources.isEmpty()) {
			multiTenantDataSource.setDefaultTargetDataSource(targetDataSources.values().iterator().next());
		}

		multiTenantDataSource.afterPropertiesSet();
		return multiTenantDataSource;
	}

	

}
