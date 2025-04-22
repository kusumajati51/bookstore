package com.example.bookstore.bookstore.dto.response;

import com.example.bookstore.bookstore.model.BookModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RewardResponseDto {
    private Long id;
    private String title;
    private Integer requiredPoints;
    private Integer stock;
    private Boolean isActive;
    private String bookTitle;
}
