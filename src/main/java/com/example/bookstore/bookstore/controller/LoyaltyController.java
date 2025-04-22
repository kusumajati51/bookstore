package com.example.bookstore.bookstore.controller;

import com.example.bookstore.bookstore.dto.request.LoyaltyPointRequestDto;
import com.example.bookstore.bookstore.dto.response.GlobalResponseDto;
import com.example.bookstore.bookstore.helper.*;
import com.example.bookstore.bookstore.model.*;
import com.example.bookstore.bookstore.service.PurchaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import  org.springframework.security.access.AccessDeniedException;

@RestController
@RequestMapping("/api/loyalty")
@RequiredArgsConstructor
public class LoyaltyController {

    private final PurchaseService purchaseService;

    @PostMapping("/points")
    public ResponseEntity<GlobalResponseDto<Integer>> getLoyaltyPoints(@RequestBody @Valid LoyaltyPointRequestDto request) {
        if (!AuthHelper.isCurrentUserAdmin()) {
            throw new AccessDeniedException("You are not authorized to access this resource");
        }
        int point = purchaseService.getTotalLoyaltyPoints(request.getEmail());

        return ResponseEntity.ok(new GlobalResponseDto<>(
                true,
                "Total loyalty points retrieved",
                point,
                null
        ));
    }

    @GetMapping("/points-user")
    public ResponseEntity<GlobalResponseDto<Integer>> getUserPoints() {
        if (AuthHelper.isCurrentUserAdmin()) {
            throw new AccessDeniedException("You are not authorized to access this resource");
        }
        UserModel user = AuthHelper.getCurrentUser();
        int point = purchaseService.getTotalLoyaltyPoints(user.getEmail());

        return ResponseEntity.ok(new GlobalResponseDto<>(
                true,
                "Total loyalty points retrieved",
                point,
                null
        ));
    }


}
