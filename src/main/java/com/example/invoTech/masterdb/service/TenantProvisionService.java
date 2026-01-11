package com.example.invoTech.masterdb.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.invoTech.masterdb.entity.Tenant;
import com.example.invoTech.masterdb.repository.TenantRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TenantProvisionService {

	private final JdbcTemplate jdbcTemplate; // Master DB JDBC
	private final TenantRepository tenantRepository;

	public Tenant registerTenant(Tenant tenant) {
		// 1️⃣ Save tenant record
		Tenant savedTenant = tenantRepository.save(tenant);

		// 2️⃣ Create tenant DB
		createDatabase(tenant.getDbName());

		// 3️⃣ Create tables
		createSchema(tenant);

		return savedTenant;
	}

	private void createDatabase(String dbName) {
		jdbcTemplate.execute("CREATE DATABASE IF NOT EXISTS " + dbName);
	}

	private void createSchema(Tenant tenant) {

		String url = "jdbc:mysql://" + tenant.getDbHost() + ":3306/" + tenant.getDbName();

		try (Connection conn = DriverManager.getConnection(url, tenant.getDbUser(), tenant.getDbPassword());
				Statement stmt = conn.createStatement()) {

			stmt.execute("""
					    CREATE TABLE IF NOT EXISTS products (
					        id BIGINT PRIMARY KEY AUTO_INCREMENT,
					        name VARCHAR(255),
					        category VARCHAR(255),
					        brand VARCHAR(255),
					        cost_price DOUBLE,
					        min_selling_price DOUBLE,
					        max_selling_price DOUBLE,
					        quantity INT
					    );
					""");

			stmt.execute("""
					    CREATE TABLE IF NOT EXISTS sales (
					        id BIGINT PRIMARY KEY AUTO_INCREMENT,
					        product_id BIGINT,
					        quantity INT,
					        price DOUBLE,
					        sale_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
					    );
					""");

			System.out.println("Tenant schema created");

		} catch (Exception e) {
			throw new RuntimeException("Schema creation failed", e);
		}
	}

}