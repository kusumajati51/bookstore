package com.example.bookstore.bookstore.repository;

import com.example.bookstore.bookstore.model.PurchaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseModel, Long> {

    // Ambil semua pembelian milik 1 user
    List<PurchaseModel> findByUserId(Long userId);
}
