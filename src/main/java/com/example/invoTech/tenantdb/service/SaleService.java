package com.example.invoTech.tenantdb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.invoTech.tenantdb.entity.Product;
import com.example.invoTech.tenantdb.entity.Sale;
import com.example.invoTech.tenantdb.entity.SaleItem;
import com.example.invoTech.tenantdb.repository.ProductRepository;
import com.example.invoTech.tenantdb.repository.SaleRepository;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;

    public SaleService(SaleRepository saleRepository, ProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Sale save(Sale sale) {
        double totalAmount = 0;
        double totalProfit = 0;

        // Calculate total and profit, update stock
        for (SaleItem item : sale.getSaleItems()) {
            Product product = item.getProduct();
            product.setQuantity(product.getQuantity() - item.getQuantity());
            productRepository.save(product);

            totalAmount += item.getTotalPrice();
            double profit = (item.getUnitPrice() - product.getCostPrice()) * item.getQuantity();
            totalProfit += profit;
        }

        sale.setTotalAmount(totalAmount);
        sale.setNetAmount(totalAmount - sale.getDiscount());

        return saleRepository.save(sale);
    }

    public List<Sale> getAll() {
        return saleRepository.findAll();
    }

    public Sale getById(Long id) {
        return saleRepository.findById(id).orElse(null);
    }
}
