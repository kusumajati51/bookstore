package com.example.bookstore.bookstore.service;

import com.example.bookstore.bookstore.dto.request.PurchaseRequestDto;
import com.example.bookstore.bookstore.dto.response.PurchaseResponseDto;
import com.example.bookstore.bookstore.dto.type_enum.*;
import com.example.bookstore.bookstore.model.*;
import com.example.bookstore.bookstore.repository.*;
import jakarta.persistence.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
    class PurchaseServiceTest {

    @Mock
    private PurchaseRepository purchaseRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PurchaseService purchaseService;

    private UserModel sampleUser;
    private BookModel sampleBook;

    @BeforeEach
    void setUp() {
        sampleUser = UserModel.builder()
                .id(1L)
                .name("Customer A")
                .email("customer@example.com")
                .password("password")
                .isAdmin(false)
                .build();

        sampleBook = BookModel.builder()
                .id(1L)
                .title("Test Book")
                .basePrice(50.0)
                .type(BookType.REGULAR)
                .isDeleted(false)
                .build();
    }

    @Test
    void createPurchase_shouldReturnResponseDto() {
        PurchaseRequestDto request = new PurchaseRequestDto();
        request.setEmail(sampleUser.getEmail());
        request.setBookId(sampleBook.getId());
        request.setQuantity(2);

        PurchaseModel saved = PurchaseModel.builder()
                .id(100L)
                .user(sampleUser)
                .book(sampleBook)
                .quantity(request.getQuantity())
                .totalPrice(100.0)
                .purchasedAt(LocalDateTime.now())
                .build();

        when(userRepository.findByEmail(sampleUser.getEmail())).thenReturn(Optional.of(sampleUser));
        when(bookRepository.findById(sampleBook.getId())).thenReturn(Optional.of(sampleBook));
        when(purchaseRepository.save(any())).thenReturn(saved);

        PurchaseResponseDto result = purchaseService.createPurchase(request);

        assertThat(result).isNotNull();
        assertThat(result.getBookTitle()).isEqualTo("Test Book");
        assertThat(result.getQuantity()).isEqualTo(2);
        assertThat(result.getTotalPrice()).isEqualTo(100.0);
    }

    @Test
    void createPurchase_shouldThrow_ifUserNotFound() {
        PurchaseRequestDto request = new PurchaseRequestDto();
        request.setEmail("notfound@example.com");
        request.setBookId(1L);
        request.setQuantity(1);

        when(userRepository.findByEmail("notfound@example.com")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> purchaseService.createPurchase(request))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("User not found");
    }

    @Test
    void createPurchase_shouldThrow_ifBookNotFound() {
        PurchaseRequestDto request = new PurchaseRequestDto();
        request.setEmail(sampleUser.getEmail());
        request.setBookId(999L);
        request.setQuantity(1);

        when(userRepository.findByEmail(sampleUser.getEmail())).thenReturn(Optional.of(sampleUser));
        when(bookRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> purchaseService.createPurchase(request))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Book not found");
    }
}
