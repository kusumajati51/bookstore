package com.example.bookstore.bookstore.service;

import com.example.bookstore.bookstore.dto.request.BookRequestDto;
import com.example.bookstore.bookstore.dto.type_enum.BookType;
import com.example.bookstore.bookstore.model.BookModel;
import com.example.bookstore.bookstore.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Validator validator;

    private BookModel sampleBook;

    @BeforeEach
    void setUp() {
        sampleBook = BookModel.builder()
                .id(1L)
                .title("Test Book")
                .basePrice(100.0)
                .type(BookType.REGULAR)
                .isDeleted(false)
                .build();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void getAllBooks_shouldReturnOnlyActiveBooks() {
        BookModel deletedBook = BookModel.builder().id(2L).title("Deleted Book").isDeleted(true).build();

        when(bookRepository.findAll()).thenReturn(List.of(sampleBook, deletedBook));

        var result = bookService.getAllBooks();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Test Book");
    }

    @Test
    void getBookById_shouldReturnBook() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(sampleBook));

        var result = bookService.getBookById(1L);

        assertThat(result.getTitle()).isEqualTo("Test Book");
    }

    @Test
    void getBookById_shouldThrowIfNotFound() {
        when(bookRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bookService.getBookById(999L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Book not found");
    }

    @Test
    void createBook_shouldSaveAndReturnBook() {
        BookRequestDto request = new BookRequestDto();
        request.setTitle("New Book");
        request.setBasePrice(200.0);
        request.setType(BookType.NEW_RELEASE);

        BookModel toSave = BookModel.builder()
                .title(request.getTitle())
                .basePrice(request.getBasePrice())
                .type(request.getType())
                .isDeleted(false)
                .build();

        when(bookRepository.save(any())).thenReturn(toSave);

        var result = bookService.createBook(request);

        assertThat(result.getTitle()).isEqualTo("New Book");
    }

    @Test
    void createBook_shouldFail_whenTitleIsBlank() {
        BookRequestDto request = new BookRequestDto();
        request.setTitle(""); // ❌ invalid
        request.setBasePrice(100.0);
        request.setType(BookType.REGULAR);

        Set<ConstraintViolation<BookRequestDto>> violations = validator.validate(request);

        assertThat(violations).isNotEmpty();
        assertThat(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("title"))).isTrue();
    }

    @Test
    void createBook_shouldFail_whenBasePriceIsNull() {
        BookRequestDto request = new BookRequestDto();
        request.setTitle("Some Book");
        request.setBasePrice(null); // ❌ invalid
        request.setType(BookType.REGULAR);

        Set<ConstraintViolation<BookRequestDto>> violations = validator.validate(request);

        assertThat(violations).isNotEmpty();
        assertThat(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("basePrice"))).isTrue();
    }

    @Test
    void createBook_shouldFail_whenTypeIsNull() {
        BookRequestDto request = new BookRequestDto();
        request.setTitle("Some Book");
        request.setBasePrice(50.0);
        request.setType(null); // ❌ invalid

        Set<ConstraintViolation<BookRequestDto>> violations = validator.validate(request);

        assertThat(violations).isNotEmpty();
        assertThat(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("type"))).isTrue();
    }

    @Test
    void updateBook_shouldUpdateFields() {
        BookRequestDto request = new BookRequestDto();
        request.setTitle("Updated Title");
        request.setBasePrice(250.0);
        request.setType(BookType.OLD_EDITION);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(sampleBook));
        when(bookRepository.save(any())).thenReturn(sampleBook);

        var result = bookService.updateBook(1L, request);

        assertThat(result.getTitle()).isEqualTo("Updated Title");
    }

    @Test
    void deleteBook_shouldSoftDelete() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(sampleBook));

        bookService.deleteBook(1L);

        verify(bookRepository, times(1)).save(argThat(book -> book.getIsDeleted()));
    }

    @Test
    void deleteBook_shouldThrowIfNotFound() {
        when(bookRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bookService.deleteBook(999L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Book not found");
    }
}
