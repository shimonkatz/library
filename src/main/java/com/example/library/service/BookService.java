package com.example.library.service;

import com.example.library.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getBooks(String bookName);

    Optional<Book> findById(Long id);

    Book createBook(Book book);

    void deleteBookById(Long id);

}
