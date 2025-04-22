package com.example.bookstore.bookstore.testing.config;

import com.example.bookstore.bookstore.dto.response.*;
import com.example.bookstore.bookstore.dto.type_enum.*;
import com.example.bookstore.bookstore.middleware.*;
import com.example.bookstore.bookstore.service.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.*;
import org.springframework.context.annotation.*;

import java.time.*;
import java.util.*;

@TestConfiguration
public class BookControllerTestConfig {

    @Bean
    public BookService bookService() {
        BookService mockService = mock(BookService.class);

        BookResponseDto sampleBook = BookResponseDto.builder()
                .id(1L)
                .title("Mocked Book")
                .basePrice(120.0)
                .type(BookType.REGULAR)
                .createdAt(LocalDateTime.now())
                .build();

        when(mockService.getAllBooks()).thenReturn(List.of(sampleBook));

        return mockService;
    }


    @Bean
    public JwtMiddleware jwtMiddleware() {
        return mock(JwtMiddleware.class); // ⬅️ Mock JWT Middleware
    }
}
