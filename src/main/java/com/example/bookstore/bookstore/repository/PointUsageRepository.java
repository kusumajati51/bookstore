package com.example.bookstore.bookstore.repository;

import com.example.bookstore.bookstore.model.PointUsageModel;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointUsageRepository extends JpaRepository<PointUsageModel, Long> {

    List<PointUsageModel> findByUserId(Long userId);
    @Query("SELECT SUM(p.pointsUsed) FROM PointUsageModel p WHERE p.user.id = :userId AND p.pointsUsed > 0")
    Integer findTotalPointsUsedByUser(@Param("userId") Long userId);
}
