package com.example.bookstore.bookstore.repository;

import com.example.bookstore.bookstore.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookModel, Long> {

}