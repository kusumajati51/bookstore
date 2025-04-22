package com.example.bookstore.bookstore.testing.config;

import com.example.bookstore.bookstore.middleware.*;
import com.example.bookstore.bookstore.repository.*;
import com.example.bookstore.bookstore.service.*;
import static org.mockito.Mockito.mock;
import org.springframework.boot.test.context.*;
import org.springframework.context.annotation.*;
import org.springframework.security.core.userdetails.*;

@TestConfiguration
public class UserControllerTestConfig {

    @Bean
    public JwtMiddleware jwtMiddleware() {
        return mock(JwtMiddleware.class);
    }

    @Bean
    public JwtService jwtService() {
        return mock(JwtService.class);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return mock(UserDetailsService.class);
    }

    @Bean
    public UserRepository userRepository() {
        return mock(UserRepository.class);
    }
}
