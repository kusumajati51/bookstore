package com.example.bookstore.bookstore.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reward_redemptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RewardRedemptionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserModel user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "reward_id")
    private RewardModel reward;

    @Column(name = "points_used", nullable = false)
    private Integer pointsUsed;

    @Column(name = "redeemed_at", nullable = false)
    private LocalDateTime redeemedAt;


    @PrePersist
    protected void onCreate() {
        redeemedAt = LocalDateTime.now();
    }
}
