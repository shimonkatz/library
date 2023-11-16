package com.example.library.controllers;

import com.example.library.entities.Book;
import com.example.library.entities.Reader;
import com.example.library.service.LendingService;
import com.example.library.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ReaderController.PATH)
@RequiredArgsConstructor
public class ReaderController {

    public static final String PATH = "/api/v1/reader";
    public static final String ID = "/{id}";

    private final ReaderService readerService;

    @GetMapping
    List<Reader> getReaders(@RequestParam(required = false) String firstName,
                            @RequestParam(required = false) String lastName) {

        return readerService.getAllReaders(firstName, lastName);
    }

    @GetMapping(ID)
    Reader getReaderById(@PathVariable Long id) {
        return readerService.getReaderById(id).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(ID)
    public void deleteReaderById(@PathVariable Long id) {
        readerService.deleteReaderById(id);
    }





}
