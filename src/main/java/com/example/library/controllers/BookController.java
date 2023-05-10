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
@RequestMapping(path = BookController.PATH)
@RequiredArgsConstructor
public class BookController {

    public static final String PATH = "/api/v1/book";
    public static final String ID= "/{id}";

    public static final String ISBN = "/isbn/{isbn}";

    private final BookService bookService;
    @GetMapping
    List<Book> getBooks(@RequestParam(required = false) String bookName){

        return bookService.getBooks(bookName);
    }

    @GetMapping(ID)
    Book getBookById(@PathVariable Long id){
        return bookService.findById(id).orElseThrow(NotFoundException::new);
    }

    @GetMapping(ISBN)
    Book getBookByIsbn(@PathVariable String isbn){
        return bookService.findByIsbn(isbn).orElseThrow(NotFoundException::new);
    }

    @PostMapping
    ResponseEntity<Book> createBook(@RequestBody Book book){
        Book savedBook = bookService.createBook(book);
        return new ResponseEntity<>(savedBook,HttpStatus.CREATED);
    }

    @DeleteMapping(ID)
    void deleteBookById(@PathVariable Long id){
        bookService.deleteBookById(id);
    }
}
