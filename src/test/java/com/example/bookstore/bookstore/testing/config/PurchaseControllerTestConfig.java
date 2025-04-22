package com.example.bookstore.bookstore.testing.config;

import com.example.bookstore.bookstore.middleware.*;
import com.example.bookstore.bookstore.service.*;
import static org.mockito.Mockito.mock;
import org.springframework.boot.test.context.*;
import org.springframework.context.annotation.*;

@TestConfiguration
public class PurchaseControllerTestConfig {
    @Bean
    public PurchaseService purchaseService() {
        return mock(PurchaseService.class);
    }

    @Bean
    public JwtService jwtService() {
        return mock(JwtService.class);
    }

    @Bean
    public JwtMiddleware jwtMiddleware() {
        return mock(JwtMiddleware.class);
    }
}
