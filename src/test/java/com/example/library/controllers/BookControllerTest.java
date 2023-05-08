package com.example.library.controllers;

import com.example.library.entities.Book;
import com.example.library.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    BookService bookService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getBooks() throws Exception {
        List<Book> books = List.of(Book.builder().
                isbn("978-1-60309-454-2").
                name("Cosmoknights (Book One)").
                author("Hannah Templer").
                available(2).
                build()
        );

        given(bookService.getBooks(null)).willReturn(books);

        mockMvc.perform(get(BookController.PATH)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(1)));
    }
}