package com.example.bookstore.bookstore.model;

import com.example.bookstore.bookstore.dto.type_enum.BookType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "base_price", nullable = false)
    private Double basePrice;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookType type; // NEW_RELEASE, REGULAR, OLD_EDITION

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
