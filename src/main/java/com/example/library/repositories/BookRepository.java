package com.example.library.repositories;

import com.example.library.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findBooksByNameIsLikeIgnoreCase(String bookName);
    Optional<Book> findBookByIsbn(String isbn);
}
