package com.example.library.controllers;

import com.example.library.entities.Reader;
import com.example.library.service.ReaderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(ReaderController.class)
class ReaderControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    ReaderService readerService;
    @Test
    void getReaders() throws Exception {
        Reader reader = Reader.builder()
                .firstName("Yael")
                .lastName("Katz")
                .build();

        given(readerService.getAllReaders(any(),any())).willReturn(List.of(reader));

        mockMvc.perform(get(ReaderController.PATH)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()",is(1)));
    }
}