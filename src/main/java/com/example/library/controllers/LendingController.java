package com.example.library.controllers;

import com.example.library.entities.Book;
import com.example.library.entities.Reader;
import com.example.library.service.BookService;
import com.example.library.service.LendingService;
import com.example.library.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = LendingController.PATH)
public class LendingController {

    public final static String PATH = "/api/v1/lending";

    private final LendingService lendingService;
    private final BookService bookService;
    private final ReaderService readerService;

    @PostMapping
    public void landBook(@RequestParam Long id, @RequestParam String isbn) {
        Reader reader = readerService.getReaderById(id).get();
        Book book = bookService.findByIsbn(isbn).get();

        lendingService.lendBook(book, reader);
    }

}
