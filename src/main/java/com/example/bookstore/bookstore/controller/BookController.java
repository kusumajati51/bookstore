package com.example.bookstore.bookstore.controller;

import com.example.bookstore.bookstore.dto.request.BookRequestDto;
import com.example.bookstore.bookstore.dto.response.BookResponseDto;
import com.example.bookstore.bookstore.dto.response.GlobalResponseDto;
import com.example.bookstore.bookstore.helper.*;
import com.example.bookstore.bookstore.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<GlobalResponseDto<List<BookResponseDto>>> getAllBooks() {

        List<BookResponseDto> books = bookService.getAllBooks();
        return ResponseEntity.ok(new GlobalResponseDto<>(
                true,
                "Books fetched successfully",
                books,
                null
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponseDto<BookResponseDto>> getBookById(@PathVariable Long id) {
        BookResponseDto book = bookService.getBookById(id);
        return ResponseEntity.ok(new GlobalResponseDto<>(
                true,
                "Book fetched successfully",
                book,
                null
        ));
    }

    @PostMapping
    public ResponseEntity<GlobalResponseDto<BookResponseDto>> createBook(@RequestBody @Valid BookRequestDto request) {
        if (!AuthHelper.isCurrentUserAdmin()) {
            throw new AccessDeniedException("You are not authorized to access this resource");
        }
        BookResponseDto created = bookService.createBook(request);
        return ResponseEntity.ok(new GlobalResponseDto<>(
                true,
                "Book created successfully",
                created,
                null
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GlobalResponseDto<BookResponseDto>> updateBook(@PathVariable Long id, @RequestBody @Valid BookRequestDto request) {
        if (!AuthHelper.isCurrentUserAdmin()) {
            throw new AccessDeniedException("You are not authorized to access this resource");
        }
        BookResponseDto updated = bookService.updateBook(id, request);
        return ResponseEntity.ok(new GlobalResponseDto<>(
                true,
                "Book updated successfully",
                updated,
                null
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalResponseDto<Void>> deleteBook(@PathVariable Long id) {
        if (!AuthHelper.isCurrentUserAdmin()) {
            throw new AccessDeniedException("You are not authorized to access this resource");
        }
        bookService.deleteBook(id);
        return ResponseEntity.ok(new GlobalResponseDto<>(
                true,
                "Book deleted successfully",
                null,
                null
        ));
    }
}
