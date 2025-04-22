package com.example.bookstore.bookstore.controller;

import com.example.bookstore.bookstore.dto.request.BookRequestDto;
import com.example.bookstore.bookstore.dto.response.BookResponseDto;
import com.example.bookstore.bookstore.dto.type_enum.BookType;
import com.example.bookstore.bookstore.service.BookService;
import com.example.bookstore.bookstore.testing.config.BookControllerTestConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
@Import(BookControllerTestConfig.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    private BookResponseDto sampleBook;

    @BeforeEach
    void setUp() {
        sampleBook = BookResponseDto.builder()
                .id(1L)
                .title("Sample Book")
                .basePrice(99.0)
                .type(BookType.REGULAR)
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getAllBooks_shouldReturnList() throws Exception {
        BookResponseDto book = BookResponseDto.builder()
                .id(1L)
                .title("Sample Book")
                .basePrice(99.0)
                .type(BookType.REGULAR)
                .createdAt(LocalDateTime.now())
                .build();

        when(bookService.getAllBooks()).thenReturn(List.of(book));

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getBookById_shouldReturnBook() throws Exception {
        when(bookService.getBookById(1L)).thenReturn(sampleBook);

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void createBook_shouldReturnCreatedBook() throws Exception {
        BookRequestDto request = new BookRequestDto();
        request.setTitle("New Book");
        request.setBasePrice(120.0);
        request.setType(BookType.NEW_RELEASE);

        when(bookService.createBook(any())).thenReturn(sampleBook);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void createBook_shouldFailValidation_whenTitleBlank() throws Exception {
        BookRequestDto request = new BookRequestDto();
        request.setTitle("");
        request.setBasePrice(100.0);
        request.setType(BookType.REGULAR);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void updateBook_shouldReturnUpdatedBook() throws Exception {
        BookRequestDto request = new BookRequestDto();
        request.setTitle("Updated Book");
        request.setBasePrice(150.0);
        request.setType(BookType.REGULAR);

        when(bookService.updateBook(eq(1L), any())).thenReturn(sampleBook);

        mockMvc.perform(put("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void deleteBook_shouldReturn200WithMessage() throws Exception {
        mockMvc.perform(delete("/api/books/1"))
                .andExpect(status().isOk());

        verify(bookService, times(1)).deleteBook(1L);
    }
}
