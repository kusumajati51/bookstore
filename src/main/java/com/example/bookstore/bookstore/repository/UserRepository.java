package com.example.bookstore.bookstore.repository;


import com.example.bookstore.bookstore.model.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByEmail(String email);
    boolean existsByEmail(String email);
}