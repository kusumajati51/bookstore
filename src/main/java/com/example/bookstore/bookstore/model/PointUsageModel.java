package com.example.bookstore.bookstore.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "point_usages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointUsageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    @Column(name = "points_used", nullable = false)
    private Integer pointsUsed;

    @Column(nullable = false)
    private String reason; // e.g., "REDEEM", "REFUND", "EXPIRED"

    @Column(name = "used_at", nullable = false)
    private LocalDateTime usedAt;

    @PrePersist
    protected void onCreate() {
        this.usedAt = LocalDateTime.now();
    }
}
