package com.example.bookstore.bookstore.controller;

import com.example.bookstore.bookstore.middleware.JwtMiddleware;
import com.example.bookstore.bookstore.repository.UserRepository;
import com.example.bookstore.bookstore.service.JwtService;
import com.example.bookstore.bookstore.testing.config.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; // ✅ get() ada di sini
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@Import(UserControllerTestConfig.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtMiddleware jwtMiddleware;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @BeforeEach
    void setUp() {
        // Optional: konfigurasi mock jika perlu
        when(jwtService.extractUsername(any())).thenReturn("tester@example.com");
    }

    @Test
    @WithMockUser(username = "tester@example.com", authorities = {"ROLE_USER"})
    void getCurrentUser_withValidToken_shouldReturnUser() throws Exception {
        mockMvc.perform(get("/api/users/me")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getCurrentUser_withoutToken_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/api/users/me")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }
}
