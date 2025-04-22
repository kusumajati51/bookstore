package com.example.bookstore.bookstore.controller;

import com.example.bookstore.bookstore.dto.request.*;
import com.example.bookstore.bookstore.dto.response.*;
import com.example.bookstore.bookstore.helper.*;
import com.example.bookstore.bookstore.model.*;
import com.example.bookstore.bookstore.service.RewardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/rewards")
@RequiredArgsConstructor
public class RewardController {

    private final RewardService rewardService;

    @PostMapping
    public ResponseEntity<GlobalResponseDto<RewardResponseDto>> create(@RequestBody @Valid RewardRequestDto request) {
        if (!AuthHelper.isCurrentUserAdmin()) {
            throw new AccessDeniedException("You are not authorized to access this resource");
        }
        RewardResponseDto created = rewardService.createReward(request);
        return ResponseEntity.ok(new GlobalResponseDto<>(
                true,
                "Reward created successfully",
                created,
                null
        ));
    }

    @GetMapping
    public ResponseEntity<GlobalResponseDto<List<RewardResponseDto>>> getAll() {
        if (!AuthHelper.isCurrentUserAdmin()) {
            throw new AccessDeniedException("You are not authorized to access this resource");
        }
        List<RewardResponseDto> rewards = rewardService.getAllRewards();
        return ResponseEntity.ok(new GlobalResponseDto<>(
                true,
                "Reward list fetched successfully",
                rewards,
                null
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GlobalResponseDto<RewardResponseDto>> update(@PathVariable Long id, @RequestBody @Valid RewardRequestDto request) {
        if (!AuthHelper.isCurrentUserAdmin()) {
            throw new AccessDeniedException("You are not authorized to access this resource");
        }
        RewardResponseDto updated = rewardService.updateReward(id, request);
        return ResponseEntity.ok(new GlobalResponseDto<>(
                true,
                "Reward updated successfully",
                updated,
                null
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalResponseDto<String>> delete(@PathVariable Long id) {
        if (!AuthHelper.isCurrentUserAdmin()) {
            throw new AccessDeniedException("You are not authorized to access this resource");
        }
        rewardService.deleteReward(id);
        return ResponseEntity.ok(new GlobalResponseDto<>(
                true,
                "Reward deleted (soft) successfully",
                "Reward ID " + id + " has been marked as deleted",
                null
        ));
    }

    @PostMapping("/history")
    public ResponseEntity<GlobalResponseDto<List<RedeemHistoryResponseDto>>> getRedeemHistory(
            @RequestBody @Valid LoyaltyPointRequestDto request) {
        if (!AuthHelper.isCurrentUserAdmin()) {
            throw new AccessDeniedException("You are not authorized to access this resource");
        }
        List<RedeemHistoryResponseDto> history = rewardService.getRedeemHistoryByEmail(request.getEmail());

        return ResponseEntity.ok(new GlobalResponseDto<>(
                true,
                "Redeem history retrieved",
                history,
                null
        ));
    }


    @PostMapping("/redeem")
    public ResponseEntity<GlobalResponseDto<String>> redeemReward(
            @RequestBody @Valid RewardRedeemRequestDto request) {
        if (!AuthHelper.isCurrentUserAdmin()) {
            throw new AccessDeniedException("You are not authorized to access this resource");
        }
        rewardService.redeemReward(request);

        return ResponseEntity.ok(new GlobalResponseDto<>(
                true,
                "Reward redeemed successfully",
                "You have successfully redeemed reward with ID: " + request.getRewardId(),
                null
        ));
    }

    @GetMapping("/redeem-history")
    public ResponseEntity<GlobalResponseDto<?>> getRedeemHistory() {
        if (AuthHelper.isCurrentUserAdmin()) {
            throw new AccessDeniedException("You are not authorized to access this resource");
        }
        UserModel user = AuthHelper.getCurrentUser();

        List<RedeemHistoryResponseDto> history = rewardService.getRedeemHistoryByEmail(user.getEmail());

        return ResponseEntity.ok(new GlobalResponseDto<>(
                true,
                "Redeem history retrieved",
                history,
                null
        ));
    }

}
    