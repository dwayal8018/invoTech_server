package com.example.invoTech.tenantdb.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.invoTech.tenantdb.entity.Notification;
import com.example.invoTech.tenantdb.entity.Product;
import com.example.invoTech.tenantdb.repository.NotificationRepository;
import com.example.invoTech.tenantdb.repository.ProductRepository;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final ProductRepository productRepository;

    public NotificationService(NotificationRepository notificationRepository, ProductRepository productRepository) {
        this.notificationRepository = notificationRepository;
        this.productRepository = productRepository;
    }

    public void checkLowStock() {
        List<Product> lowStockProducts = productRepository.findAll().stream()
                .filter(p -> p.getQuantity() <= 10) // Assuming reorder limit is 10
                .toList();

        for (Product product : lowStockProducts) {
            Notification notification = new Notification();
            notification.setType("LowStock");
            notification.setMessage("Low stock for product: " + product.getName());
            notification.setCreatedAt(java.time.LocalDateTime.now());
            notification.setIsRead(false);
            notificationRepository.save(notification);
        }
    }

    public void checkExpiry() {
        LocalDate now = LocalDate.now();
        LocalDate warningDate = now.plusDays(30); // Expiry warning 30 days before

        List<Product> expiringProducts = productRepository.findAll().stream()
                .filter(p -> p.getExpiryDate() != null && p.getExpiryDate().isBefore(warningDate))
                .toList();

        for (Product product : expiringProducts) {
            Notification notification = new Notification();
            notification.setType("Expiry");
            notification.setMessage("Product expiring soon: " + product.getName());
            notification.setCreatedAt(java.time.LocalDateTime.now());
            notification.setIsRead(false);
            notificationRepository.save(notification);
        }
    }

    public List<Notification> getAll() {
        return notificationRepository.findAll();
    }

    public void markAsRead(Long id) {
        Notification notification = notificationRepository.findById(id).orElse(null);
        if (notification != null) {
            notification.setIsRead(true);
            notificationRepository.save(notification);
        }
    }
}
