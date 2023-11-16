package com.example.library.service;

import com.example.library.entities.Book;
import com.example.library.entities.Reader;

public interface LendingService {

    void lendBook(Book book, Reader reader);
    void returnBook(Book book, Reader reader);

}
