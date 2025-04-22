package com.example.bookstore.bookstore.controller;

import com.example.bookstore.bookstore.dto.request.LoginRequestDto;
import com.example.bookstore.bookstore.dto.response.GlobalResponseDto;
import com.example.bookstore.bookstore.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void loginSuccess_shouldReturn200AndToken() throws Exception {
        LoginRequestDto loginRequest = new LoginRequestDto();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password");

        when(authService.login(loginRequest.getEmail(), loginRequest.getPassword()))
                .thenReturn("mock-token");

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(true))
                .andExpect(jsonPath("$.message").value("Login successful"))
                .andExpect(jsonPath("$.data.token").value("mock-token"))
                .andExpect(jsonPath("$.error").doesNotExist());
    }

    @Test
    void loginInvalid_shouldReturn401() throws Exception {
        LoginRequestDto loginRequest = new LoginRequestDto();
        loginRequest.setEmail("wrong@example.com");
        loginRequest.setPassword("wrongpass");

        when(authService.login(any(), any()))
                .thenThrow(new RuntimeException("Invalid credentials"));

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.status").value(false))
                .andExpect(jsonPath("$.message").value("Login failed"))
                .andExpect(jsonPath("$.data").doesNotExist())
                .andExpect(jsonPath("$.error").exists());
    }
}
