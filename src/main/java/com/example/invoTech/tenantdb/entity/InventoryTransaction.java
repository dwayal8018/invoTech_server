package com.example.invoTech.tenantdb.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "inventory_transactions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class InventoryTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private String transactionType; // PURCHASE, SALE, ADJUSTMENT
    private Integer quantity;
    private Double unitPrice;
    private LocalDateTime transactionDate;
    private String reference; // Purchase ID, Sale ID, etc.
}
