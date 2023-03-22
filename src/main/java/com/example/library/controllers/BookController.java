package com.example.library.controllers;

import com.example.library.entities.Book;
import com.example.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    @GetMapping
    List<Book> getBooks(){
        return bookService.getBooks();
    }

    @GetMapping("/{id}")
    ResponseEntity<Book> getBookById(@PathVariable Long id){
        Optional<Book> book = bookService.findById(id);
        return new ResponseEntity<>(book.get(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Book> createBook(@RequestBody Book book){
        Book savedBook = bookService.createBook(book);
        return new ResponseEntity<>(savedBook,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    void deleteBookById(@PathVariable Long id){
        bookService.deleteBookById(id);
    }
}
