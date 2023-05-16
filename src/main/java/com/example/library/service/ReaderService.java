package com.example.library.service;

import com.example.library.entities.Reader;

import java.util.List;
import java.util.Optional;

public interface ReaderService {

    List<Reader> getAllReaders(String firstName,String lastName);

    Optional<Reader> getReaderById(Long id);

    Reader createReader(Reader reader);
    void deleteReaderById(Long id);


}
