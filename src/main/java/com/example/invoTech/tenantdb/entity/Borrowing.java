package com.example.invoTech.tenantdb.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "borrowings")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String customerPhone;
    private Double totalAmount;
    private Double paidAmount;
    private Double remainingAmount;
    private LocalDateTime borrowDate;
    private LocalDateTime dueDate;
    private String status; // Active, Closed
}
