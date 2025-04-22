package com.example.bookstore.bookstore.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RedeemHistoryResponseDto {
    private String rewardTitle;
    private Integer pointsUsed;
    private LocalDateTime redeemedAt;
}
