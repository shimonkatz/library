package com.example.library.bootstrap;

import com.example.library.entities.Book;
import com.example.library.entities.Reader;
import com.example.library.repositories.BookRepository;
import com.example.library.repositories.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UploadData implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;

    @Override
    public void run(String... args) throws Exception {
        loadBooks();
        loadReaders();
    }

    private void loadBooks() {
        List<Book> books = List.of(
                Book.builder().
                        isbn("978-1-60309-454-2").
                        name("Cosmoknights (Book One)").
                        author("Hannah Templer").
                        available(2).
                        build(),
                Book.builder().
                        isbn("978-1-60309-454-3").
                        name("Cosmoknights (Book Two)").
                        author("Hannah Templer").
                        available(2).
                        build(),
                Book.builder().
                        isbn("978-1-60309-454-4").
                        name("Zoo Story").
                        author("Duane Murray;Hannah Templer").
                        available(4).
                        build(),
                Book.builder().
                        isbn("978-1-60309-495-5").
                        name("Better Place").
                        author("Duane Murray;Shawn Daley").
                        available(5).
                        build(),
                Book.builder().
                        isbn("978-1-891830-69-3").
                        name("Lone Racer").
                        author("Nicolas Mahler").
                        available(3).
                        build()
        );

        List<Book> bookList = bookRepository.findAll();
        if (bookList.isEmpty()) {
            bookRepository.saveAll(books);
        }
    }

    private void loadReaders() {
        List<Reader> readers = List.of(
                Reader.builder()
                        .firstName("Yael")
                        .lastName("Katz")
                        .build(),
                Reader.builder()
                        .firstName("Shimon")
                        .lastName("Katz")
                        .build(),
                Reader.builder()
                        .firstName("Noam")
                        .lastName("Katz")
                        .build(),
                Reader.builder()
                        .firstName("Noam")
                        .lastName("Levy")
                        .build(),
                Reader.builder()
                        .firstName("Yael")
                        .lastName("Levy")
                        .build()
        );

        List<Reader> readerList = readerRepository.findAll();
        if (readerList.isEmpty()) {
            readerRepository.saveAll(readers);
        }
    }
}
