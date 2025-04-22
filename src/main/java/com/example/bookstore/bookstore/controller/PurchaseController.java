package com.example.bookstore.bookstore.controller;

import com.example.bookstore.bookstore.dto.request.PurchaseRequestDto;
import com.example.bookstore.bookstore.dto.response.GlobalResponseDto;
import com.example.bookstore.bookstore.dto.response.PurchaseResponseDto;
import com.example.bookstore.bookstore.helper.*;
import com.example.bookstore.bookstore.service.PurchaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<GlobalResponseDto<PurchaseResponseDto>> createPurchase(@RequestBody @Valid PurchaseRequestDto request) {
        if (!AuthHelper.isCurrentUserAdmin()) {
            throw new AccessDeniedException("You are not authorized to access this resource");
        }
        PurchaseResponseDto result = purchaseService.createPurchase(request);
        return ResponseEntity.ok(new GlobalResponseDto<>(
                true,
                "Purchase successful",
                result,
                null
        ));
    }

    @GetMapping
    public ResponseEntity<GlobalResponseDto<List<PurchaseResponseDto>>> getUserPurchases() {
        if (!AuthHelper.isCurrentUserAdmin()) {
            throw new AccessDeniedException("You are not authorized to access this resource");
        }
        List<PurchaseResponseDto> results = purchaseService.getPurchasesByUser();
        return ResponseEntity.ok(new GlobalResponseDto<>(
                true,
                "Purchase history loaded",
                results,
                null
        ));
    }
}
