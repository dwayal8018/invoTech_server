package com.example.invoTech.tenantdb.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sales")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime saleDate;
    private String customerName;
    private String customerPhone;
    private Double totalAmount;
    private Double discount;
    private Double netAmount;
    private String paymentMethod; // Cash, Card, Credit
    private String status; // Completed, Pending

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<SaleItem> saleItems;
}
