package com.example.library.controllers;

import com.example.library.entities.Book;
import com.example.library.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    BookService bookService;

    @Autowired
    ObjectMapper objectMapper;

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

    @Test
    void getBooksByName() throws Exception {

        List<Book> books = List.of(Book.builder().
                isbn("978-1-60309-454-2").
                name("Cosmoknights (Book One)").
                author("Hannah Templer").
                available(2).
                build()
        );

        given(bookService.getBooks("%book%")).willReturn(books);

        mockMvc.perform(get(BookController.PATH)
                        .queryParam("bookName", "%book%")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(1)));
    }

    @Test
    void getBookByIsbn() throws Exception {
        Book book = Book.builder().
                isbn("978-1-60309-454-2").
                name("Cosmoknights (Book One)").
                author("Hannah Templer").
                available(2).
                build();

        given(bookService.findByIsbn(any())).willReturn(Optional.of(book));

        mockMvc.perform(get(BookController.PATH +BookController.ISBN,"978-1-60309-454-2"))
                .andExpect(jsonPath("$.isbn", is("978-1-60309-454-2")));;
    }

    @Test
    void getBookByIsbnNotFound() throws Exception {
        Book book = Book.builder().
                isbn("978-1-60309-454-2").
                name("Cosmoknights (Book One)").
                author("Hannah Templer").
                available(2).
                build();

        given(bookService.findByIsbn(any())).willThrow(NotFoundException.class);

        mockMvc.perform(get(BookController.PATH +BookController.ISBN,"978-1-60309-454-2"))
                .andExpect(status().isNotFound());;
    }

    @Test
    void saveBook() throws Exception {
      Book book = Book.builder().
              isbn("978-1-60309-454-2").
              name("Cosmoknights (Book One)").
              author("Hannah Templer").
              available(2).
              build();

      given(bookService.createBook(any())).willReturn(book);

      mockMvc.perform(post(BookController.PATH)
              .accept(MediaType.APPLICATION_JSON)
              .contentType(MediaType.APPLICATION_JSON)
              .content(objectMapper.writeValueAsString(book)))
              .andExpect(status().isCreated());
    }

    @Test
    void saveEmptyBook() throws Exception {
        Book book = Book.builder().build();

        given(bookService.createBook(any())).willReturn(book);

        MvcResult mvcResult = mockMvc.perform(post(BookController.PATH)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isBadRequest()).andReturn();

    }
}