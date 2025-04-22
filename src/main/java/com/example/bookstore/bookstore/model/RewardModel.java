    package com.example.bookstore.bookstore.model;

    import jakarta.persistence.*;
    import lombok.*;

    import java.time.*;

    @Entity
    @Table(name = "rewards")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class RewardModel {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String title;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "book_id")
        private BookModel book;

        @Column(name = "required_points", nullable = false)
        private Integer requiredPoints;

        @Column(nullable = false)
        private Integer stock;

        @Column(nullable = false)
        private Boolean isActive = true;

        @Column(name = "is_deleted", nullable = false)
        private Boolean isDeleted = false;

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
