package com.example.bookstore.bookstore.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RewardRequestDto {

    @NotBlank(message = "Title is required")
    private String title;

    private Long bookId; // Opsional, jika reward terhubung dengan buku

    @NotNull(message = "Required points is required")
    @Min(value = 1, message = "Points must be at least 1")
    private Integer requiredPoints;

    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock must be 0 or more")
    private Integer stock;

    private Boolean isActive = true;
}
