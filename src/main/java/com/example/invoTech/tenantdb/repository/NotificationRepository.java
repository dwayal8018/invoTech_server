package com.example.invoTech.tenantdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.invoTech.tenantdb.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
