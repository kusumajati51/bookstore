package com.example.bookstore.bookstore.helper;

import com.example.bookstore.bookstore.model.UserModel;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthHelper {

    public static UserModel getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserModel user) {
            return user;
        }
        return null;
    }

    public static boolean isCurrentUserAdmin() {
        UserModel user = getCurrentUser();
        return user != null && Boolean.TRUE.equals(user.getIsAdmin());
    }
}
