package com.example.invoTech.masterdb.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.invoTech.masterdb.entity.Tenant;

@Repository
public class TenantRepository {

	private final JdbcTemplate jdbcTemplate;

	public TenantRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Tenant save(Tenant tenant) {
		if (tenant.getTenantId() == null) {
			// Insert new tenant
			String sql = "INSERT INTO tenants (shop_name, owner_name, username, password_hash, db_name, db_host, db_user, db_password, status, created_on) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			jdbcTemplate.update(sql, tenant.getShopName(), tenant.getOwnerName(), tenant.getUsername(),
					tenant.getPasswordHash(), tenant.getDbName(), tenant.getDbHost(), tenant.getDbUser(),
					tenant.getDbPassword(), tenant.getStatus().toString(), tenant.getCreatedOn());

			// Get the generated ID
			Long id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
			tenant.setTenantId(id);
		} else {
			// Update existing tenant
			String sql = "UPDATE tenants SET shop_name=?, owner_name=?, username=?, password_hash=?, db_name=?, db_host=?, db_user=?, db_password=?, status=? WHERE tenant_id=?";
			jdbcTemplate.update(sql, tenant.getShopName(), tenant.getOwnerName(), tenant.getUsername(),
					tenant.getPasswordHash(), tenant.getDbName(), tenant.getDbHost(), tenant.getDbUser(),
					tenant.getDbPassword(), tenant.getStatus().toString(), tenant.getTenantId());
		}
		return tenant;
	}

	public Optional<Tenant> findByUsername(String username) {
		String sql = "SELECT * FROM tenants WHERE username = ?";
		try {
			Tenant tenant = jdbcTemplate.queryForObject(sql, new TenantRowMapper(), username);
			return Optional.of(tenant);
		} catch (Exception e) {
			return Optional.empty();
		}
	}

    public Optional<Tenant> findById(Long id) {
        String sql = "SELECT * FROM tenants WHERE tenant_id = ?";
        try {
            Tenant tenant = jdbcTemplate.queryForObject(sql, new TenantRowMapper(), id);
            return Optional.of(tenant);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public List<Tenant> findAll() {
        String sql = "SELECT * FROM tenants";
        return jdbcTemplate.query(sql, new TenantRowMapper());
    }

	private static class TenantRowMapper implements RowMapper<Tenant> {
		@Override
		public Tenant mapRow(ResultSet rs, int rowNum) throws SQLException {
			Tenant tenant = new Tenant();
			tenant.setTenantId(rs.getLong("tenant_id"));
			tenant.setShopName(rs.getString("shop_name"));
			tenant.setOwnerName(rs.getString("owner_name"));
			tenant.setUsername(rs.getString("username"));
			tenant.setPasswordHash(rs.getString("password_hash"));
			tenant.setDbName(rs.getString("db_name"));
			tenant.setDbHost(rs.getString("db_host"));
			tenant.setDbUser(rs.getString("db_user"));
			tenant.setDbPassword(rs.getString("db_password"));
			tenant.setStatus(Tenant.Status.valueOf(rs.getString("status")));
			tenant.setCreatedOn(rs.getTimestamp("created_on").toLocalDateTime());
			return tenant;
		}
	}

}
