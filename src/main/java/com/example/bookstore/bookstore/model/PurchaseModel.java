package com.example.bookstore.bookstore.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "purchases")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relasi ke UserModel
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    // Relasi ke BookModel
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private BookModel book;

    // Jumlah buku yang dibeli
    @Column(nullable = false)
    private Integer quantity;

    // Total harga pembelian (basePrice * quantity)
    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Column(name = "purchased_at", nullable = false)
    private LocalDateTime purchasedAt;

    @PrePersist
    protected void onCreate() {
        this.purchasedAt = LocalDateTime.now();
    }
}
