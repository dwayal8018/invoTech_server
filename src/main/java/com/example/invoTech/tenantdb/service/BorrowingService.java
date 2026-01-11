package com.example.invoTech.tenantdb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.invoTech.tenantdb.entity.Borrowing;
import com.example.invoTech.tenantdb.repository.BorrowingRepository;

@Service
public class BorrowingService {

    private final BorrowingRepository borrowingRepository;

    public BorrowingService(BorrowingRepository borrowingRepository) {
        this.borrowingRepository = borrowingRepository;
    }

    public Borrowing save(Borrowing borrowing) {
        borrowing.setRemainingAmount(borrowing.getTotalAmount() - borrowing.getPaidAmount());
        return borrowingRepository.save(borrowing);
    }

    public List<Borrowing> getAll() {
        return borrowingRepository.findAll();
    }

    public Borrowing getById(Long id) {
        return borrowingRepository.findById(id).orElse(null);
    }

    public Borrowing updatePayment(Long id, Double payment) {
        Borrowing borrowing = getById(id);
        if (borrowing != null) {
            borrowing.setPaidAmount(borrowing.getPaidAmount() + payment);
            borrowing.setRemainingAmount(borrowing.getTotalAmount() - borrowing.getPaidAmount());
            if (borrowing.getRemainingAmount() <= 0) {
                borrowing.setStatus("Closed");
            }
            return save(borrowing);
        }
        return null;
    }
}
