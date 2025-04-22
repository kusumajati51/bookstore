package com.example.bookstore.bookstore.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.*;

import java.util.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "is_admin", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isAdmin = false;

    // Tambahan: createdAt dan updatedAt (optional)
    @Column(name = "created_at", updatable = false)
    private java.time.LocalDateTime createdAt;

    @Column(name = "updated_at")
    private java.time.LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = java.time.LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = java.time.LocalDateTime.now();
    }

    // Implementasi dari UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(); // Bisa diisi ROLE_USER kalau nanti pakai role
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}