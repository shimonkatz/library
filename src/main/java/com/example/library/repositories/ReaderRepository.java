package com.example.library.repositories;

import com.example.library.entities.Book;
import com.example.library.entities.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReaderRepository extends JpaRepository<Reader,Long> {

    List<Reader> findAll();

    List<Reader> findAllByFirstName(String firstName);
    List<Reader> findAllByLastName(String lastName);

    List<Reader> findAllByFirstNameAndLastName(String firstName,String lastName);
}
