package com.example.library.service;

import com.example.library.entities.Reader;
import com.example.library.repositories.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReaderServiceImpl implements ReaderService {
    private final ReaderRepository readerRepository;
    @Override
    public List<Reader> getAllReaders(String firstName, String lastName) {
        if (StringUtils.hasText(firstName) && !StringUtils.hasText(lastName)) {
            return getReadersByFirstName(firstName);
        } else if (!StringUtils.hasText(firstName) && StringUtils.hasText(lastName)) {
            return getReadersByLastName(lastName);
        } else if (StringUtils.hasText(firstName) && StringUtils.hasText(lastName)) {
            return getReadersByFirstNameAndByLastName(firstName, lastName);
        } else {
            return readerRepository.findAll();
        }
    }

    List<Reader> getReadersByFirstName(String firstName) {
        return readerRepository.findAllByFirstName(firstName);
    }

    List<Reader> getReadersByLastName(String lastName) {
        return readerRepository.findAllByLastName(lastName);
    }

    List<Reader> getReadersByFirstNameAndByLastName(String firstName, String lastName) {
        return readerRepository.findAllByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public Optional<Reader> getReaderById(Long id) {
        return readerRepository.findById(id);
    }

    @Override
    public Reader createReader(Reader reader) {
        return readerRepository.save(reader);
    }

    @Override
    public void deleteReaderById(Long id) {
        readerRepository.deleteById(id);
    }
}
