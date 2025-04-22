package com.example.bookstore.bookstore.controller;

import com.example.bookstore.bookstore.dto.request.*;
import com.example.bookstore.bookstore.dto.response.*;
import com.example.bookstore.bookstore.model.UserModel;
import com.example.bookstore.bookstore.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<GlobalResponseDto<AuthResponseDto>> register(@Valid @RequestBody RegisterRequestDto request) {
        try {

            String token = authService.register(request);

            return ResponseEntity.ok(GlobalResponseDto.<AuthResponseDto>builder()
                    .status(true)
                    .message("User registered successfully")
                    .data(new AuthResponseDto(token))
                    .error(null)
                    .build());

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(GlobalResponseDto.<AuthResponseDto>builder()
                    .status(false)
                    .message("Failed to register user")
                    .data(null)
                    .error(ex.getMessage())
                    .build());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<GlobalResponseDto<AuthResponseDto>> login(@Valid @RequestBody LoginRequestDto request) {
        try {
            String token = authService.login(request.getEmail(), request.getPassword());

            return ResponseEntity.ok(GlobalResponseDto.<AuthResponseDto>builder()
                    .status(true)
                    .message("Login successful")
                    .data(new AuthResponseDto(token))
                    .error(null)
                    .build());

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(GlobalResponseDto.<AuthResponseDto>builder()
                    .status(false)
                    .message("Login failed")
                    .data(null)
                    .error(ex.getMessage())
                    .build());
        }
    }

    @PostMapping("/register-admin")
    public ResponseEntity<GlobalResponseDto<Map<String, String>>> registerAdmin(
            @RequestBody @Valid AdminRegisterRequestDto request) {

        String token = authService.registerAdmin(request);

        return ResponseEntity.ok(new GlobalResponseDto<>(
                true,
                "Admin registered successfully",
                Map.of("token", token),
                null
        ));
    }

}
