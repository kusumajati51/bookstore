package com.example.bookstore.bookstore.dto.response;

import com.example.bookstore.bookstore.dto.type_enum.BookType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PurchaseResponseDto {
    private Long id;
    private String bookTitle;
    private BookType bookType;
    private Integer quantity;
    private Double totalPrice;
    private LocalDateTime purchasedAt;
}
