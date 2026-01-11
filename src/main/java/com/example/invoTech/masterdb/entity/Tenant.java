package com.example.invoTech.masterdb.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tenants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tenant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tenantId;

	private String shopName;
	private String ownerName;
	private String username;
	private String passwordHash;
	private String dbName;
	private String dbHost;
	private String dbUser;
	private String dbPassword;

	@Enumerated(EnumType.STRING)
	private Status status = Status.ACTIVE;

	private LocalDateTime createdOn = LocalDateTime.now();

	public enum Status {
		ACTIVE, SUSPENDED, PENDING
	}
}
