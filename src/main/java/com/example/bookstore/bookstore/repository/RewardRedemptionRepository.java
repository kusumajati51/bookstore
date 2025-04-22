package com.example.bookstore.bookstore.repository;

import com.example.bookstore.bookstore.model.RewardRedemptionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardRedemptionRepository extends JpaRepository<RewardRedemptionModel, Long> {

    // Log penukaran hadiah oleh user
    List<RewardRedemptionModel> findByUserId(Long userId);
}
