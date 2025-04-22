package com.example.bookstore.bookstore.dto.request;

import com.example.bookstore.bookstore.dto.type_enum.BookType;
import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class BookRequestDto {

    @NotBlank
    private String title;

    @NotNull
    @Positive
    private Double basePrice;

    @NotNull
    private BookType type;
}
