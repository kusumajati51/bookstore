package com.example.bookstore.bookstore.dto.response;

import com.example.bookstore.bookstore.dto.type_enum.BookType;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponseDto {

    private Long id;
    private String title;
    private Double basePrice;
    private BookType type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
