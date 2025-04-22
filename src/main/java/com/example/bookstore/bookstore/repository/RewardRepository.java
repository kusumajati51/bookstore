package com.example.bookstore.bookstore.repository;

import com.example.bookstore.bookstore.model.RewardModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardRepository extends JpaRepository<RewardModel, Long> {

    // Ambil hanya reward yang aktif dan punya stok
    List<RewardModel> findByIsActiveTrueAndStockGreaterThan(int minimumStock);
}
