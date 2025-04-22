package com.example.bookstore.bookstore.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PurchaseRequestDto {

    @NotNull(message = "bookId is required")
    private Long bookId;

    @NotNull(message = "quantity is required")
    @Min(value = 1, message = "Minimum quantity is 1")
    private Integer quantity;
    @NotNull(message = "email is required")

    @Email(message = "email must be valid")
    private String email;
}
