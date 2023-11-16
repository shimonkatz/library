package com.example.library.service;

import com.example.library.entities.Book;
import com.example.library.entities.Lending;
import com.example.library.entities.LendingStatus;
import com.example.library.entities.Reader;
import com.example.library.repositories.LendingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LendingServiceImpl implements LendingService {

    private final LendingRepository lendingRepository;
    @Override
    public void lendBook(Book book, Reader reader) {

        // TODO : we need to check some constraints before

        Lending lending = Lending.builder()
                .status(LendingStatus.OPEN)
                .reader(reader)
                .book(book)
                .startLendingTime(LocalDateTime.now())
                .build();
        lendingRepository.save(lending);
    }

    @Override
    public void returnBook(Book book, Reader reader) {

    }
}
