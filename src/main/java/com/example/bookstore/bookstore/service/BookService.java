package com.example.bookstore.bookstore.service;

import com.example.bookstore.bookstore.dto.request.BookRequestDto;
import com.example.bookstore.bookstore.dto.response.BookResponseDto;
import com.example.bookstore.bookstore.model.BookModel;
import com.example.bookstore.bookstore.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<BookResponseDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .filter(book -> !book.getIsDeleted()) // ⬅️ ini penting
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public BookResponseDto getBookById(Long id) {
        BookModel book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
        return mapToDto(book);
    }

    public BookResponseDto createBook(BookRequestDto request) {
        BookModel book = BookModel.builder()
                .title(request.getTitle())
                .basePrice(request.getBasePrice())
                .type(request.getType())
                .isDeleted(false)
                .build();

        return mapToDto(bookRepository.save(book));
    }

    public BookResponseDto updateBook(Long id, BookRequestDto request) {
        BookModel book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        book.setTitle(request.getTitle());
        book.setBasePrice(request.getBasePrice());
        book.setType(request.getType());

        return mapToDto(bookRepository.save(book));
    }

    public void deleteBook(Long id) {
        BookModel book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        book.setIsDeleted(true);
        bookRepository.save(book);
    }

    private BookResponseDto mapToDto(BookModel book) {
        return BookResponseDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .basePrice(book.getBasePrice())
                .type(book.getType())
                .createdAt(book.getCreatedAt())
                .updatedAt(book.getUpdatedAt())
                .build();
    }
}
