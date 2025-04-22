package com.example.bookstore.bookstore.service;

import com.example.bookstore.bookstore.dto.request.*;
import com.example.bookstore.bookstore.dto.response.*;
import com.example.bookstore.bookstore.model.*;
import com.example.bookstore.bookstore.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RewardService {

    private final UserRepository userRepository;
    private final RewardRepository rewardRepository;
    private final PointUsageRepository pointUsageRepository;
    private final RewardRedemptionRepository rewardRedemptionRepository;
    private final BookRepository bookRepository;

    public RewardResponseDto createReward(RewardRequestDto request) {
        BookModel book = null;
        if (request.getBookId() != null) {
            book = bookRepository.findById(request.getBookId())
                    .orElseThrow(() -> new EntityNotFoundException("Book not found"));
        }

        RewardModel saved = rewardRepository.save(RewardModel.builder()
                .title(request.getTitle())
                .book(book)
                .requiredPoints(request.getRequiredPoints())
                .stock(request.getStock())
                .isActive(request.getIsActive())
                .isDeleted(false)
                .build());

        return toDto(saved);
    }

    public List<RewardResponseDto> getAllRewards() {
        return rewardRepository.findAll().stream()
                .filter(r -> !r.getIsDeleted())
                .map(this::toDto)
                .toList();
    }

    public RewardResponseDto updateReward(Long id, RewardRequestDto request) {
        RewardModel reward = rewardRepository.findById(id)
                .filter(r -> !r.getIsDeleted())
                .orElseThrow(() -> new EntityNotFoundException("Reward not found"));

        if (request.getBookId() != null) {
            BookModel book = bookRepository.findById(request.getBookId())
                    .orElseThrow(() -> new EntityNotFoundException("Book not found"));
            reward.setBook(book);
        }

        reward.setTitle(request.getTitle());
        reward.setRequiredPoints(request.getRequiredPoints());
        reward.setStock(request.getStock());
        reward.setIsActive(request.getIsActive());

        rewardRepository.save(reward);

        return toDto(reward);
    }


    public void deleteReward(Long id) {
        RewardModel reward = rewardRepository.findById(id)
                .filter(r -> !r.getIsDeleted())
                .orElseThrow(() -> new EntityNotFoundException("Reward not found"));

        reward.setIsDeleted(true);
        rewardRepository.save(reward);
    }


    public void redeemReward(RewardRedeemRequestDto request) {
        UserModel user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        RewardModel reward = rewardRepository.findById(request.getRewardId())
                .orElseThrow(() -> new EntityNotFoundException("Reward not found"));

        if (!reward.getIsActive()) {
            throw new IllegalStateException("Reward is not active");
        }

        if (reward.getStock() <= 0) {
            throw new IllegalStateException("Reward is out of stock");
        }

        // Hitung total poin user
        int totalPoints = calculateUserPoint(user);

        if (totalPoints < reward.getRequiredPoints()) {
            throw new IllegalArgumentException("Not enough points to redeem this reward");
        }

        // Kurangi stok reward
        reward.setStock(reward.getStock() - 1);
        rewardRepository.save(reward);

        // Simpan log pengurangan poin
        pointUsageRepository.save(PointUsageModel.builder()
                .user(user)
                .pointsUsed(reward.getRequiredPoints())
                .reason("REDEEM")
                .build());

        // Simpan log penukaran hadiah
        rewardRedemptionRepository.save(RewardRedemptionModel.builder()
                .user(user)
                .reward(reward)
                .pointsUsed(reward.getRequiredPoints())
                .build());
    }

    private int calculateUserPoint(UserModel user) {
        var usages = pointUsageRepository.findByUserId(user.getId());

        int earned = usages.stream()
                .filter(u -> "EARN".equalsIgnoreCase(u.getReason()))
                .mapToInt(PointUsageModel::getPointsUsed)
                .sum();

        int used = usages.stream()
                .filter(u -> !"EARN".equalsIgnoreCase(u.getReason()))
                .mapToInt(PointUsageModel::getPointsUsed)
                .sum();

        return earned - used;
    }

    public List<RedeemHistoryResponseDto> getRedeemHistoryByEmail(String email) {
        UserModel user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        return rewardRedemptionRepository.findByUserId(user.getId()).stream()
                .map(log -> RedeemHistoryResponseDto.builder()
                        .rewardTitle(log.getReward().getTitle())
                        .pointsUsed(log.getPointsUsed())
                        .redeemedAt(log.getRedeemedAt())
                        .build())
                .toList();
    }



    private RewardResponseDto toDto(RewardModel model) {
        return RewardResponseDto.builder()
                .id(model.getId())
                .title(model.getTitle())
                .requiredPoints(model.getRequiredPoints())
                .stock(model.getStock())
                .isActive(model.getIsActive())
                .bookTitle(model.getBook() != null ? model.getBook().getTitle() : null)
                .build();
    }


}
