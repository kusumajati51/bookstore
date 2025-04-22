package com.example.bookstore.bookstore.controller;

import com.example.bookstore.bookstore.dto.request.PurchaseRequestDto;
import com.example.bookstore.bookstore.dto.response.PurchaseResponseDto;
import com.example.bookstore.bookstore.dto.type_enum.BookType;
import com.example.bookstore.bookstore.service.PurchaseService;
import com.example.bookstore.bookstore.testing.config.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.context.*;
import org.springframework.context.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PurchaseController.class)
@Import(PurchaseControllerTestConfig.class)
class PurchaseControllerTest {

    @Autowired private MockMvc mockMvc;

    @Autowired private PurchaseService purchaseService;

    @Autowired private ObjectMapper objectMapper;

    private PurchaseResponseDto samplePurchase;

    @Autowired
    ApplicationContext context;

    @BeforeEach
    void printBeans() {
        System.out.println("✅ Loaded Beans:");
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
    }

    @BeforeEach
    void setUp() {
        samplePurchase = PurchaseResponseDto.builder()
                .id(1L)
                .bookTitle("Test Book")
                .bookType(BookType.REGULAR)
                .quantity(2)
                .totalPrice(100.0)
                .purchasedAt(LocalDateTime.now())
                .build();
    }

    @Test
    @WithMockUser(username = "kasir", roles = {"ADMIN"})
    void createPurchase_shouldReturn200AndData() throws Exception {
        PurchaseRequestDto request = new PurchaseRequestDto();
        request.setEmail("customer@example.com");
        request.setBookId(1L);
        request.setQuantity(2);

        when(purchaseService.createPurchase(any())).thenReturn(samplePurchase);

        mockMvc.perform(post("/api/purchases")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(true))
                .andExpect(jsonPath("$.data.bookTitle").value("Test Book"))
                .andExpect(jsonPath("$.message").value("Purchase successful"));
    }

    @Test
    @WithMockUser(username = "kasir", roles = {"ADMIN"})
    void getUserPurchases_shouldReturnList() throws Exception {
        when(purchaseService.getPurchasesByUser()).thenReturn(List.of(samplePurchase));

        mockMvc.perform(get("/api/purchases"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(true))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].bookTitle").value("Test Book"))
                .andExpect(jsonPath("$.message").value("Purchase history loaded"));
    }
}
