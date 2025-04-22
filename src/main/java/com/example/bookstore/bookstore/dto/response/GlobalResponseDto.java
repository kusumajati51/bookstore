package com.example.bookstore.bookstore.dto.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GlobalResponseDto<T> {
    private boolean status;
    private String message;
    private T data;
    private Object error;
}