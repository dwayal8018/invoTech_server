package com.example.invoTech.tenantdb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.invoTech.tenantdb.entity.Product;
import com.example.invoTech.tenantdb.entity.Purchase;
import com.example.invoTech.tenantdb.entity.PurchaseItem;
import com.example.invoTech.tenantdb.repository.ProductRepository;
import com.example.invoTech.tenantdb.repository.PurchaseRepository;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;

    public PurchaseService(PurchaseRepository purchaseRepository, ProductRepository productRepository) {
        this.purchaseRepository = purchaseRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Purchase save(Purchase purchase) {
        // Update stock for each purchase item
        for (PurchaseItem item : purchase.getPurchaseItems()) {
            Product product = item.getProduct();
            product.setQuantity(product.getQuantity() + item.getQuantity());
            productRepository.save(product);
        }
        return purchaseRepository.save(purchase);
    }

    public List<Purchase> getAll() {
        return purchaseRepository.findAll();
    }

    public Purchase getById(Long id) {
        return purchaseRepository.findById(id).orElse(null);
    }
}
