package com.example.bookstore.bookstore.controller;

import com.example.bookstore.bookstore.dto.response.GlobalResponseDto;
import com.example.bookstore.bookstore.model.UserModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/me")
    public ResponseEntity<GlobalResponseDto<UserModel>> getCurrentUser(@AuthenticationPrincipal UserModel user) {
        return ResponseEntity.ok(GlobalResponseDto.<UserModel>builder()
                .status(true)
                .message("Current user data")
                .data(user)
                .error(null)
                .build());
    }
}
