package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.model.TShirt;
import com.company.gamestore.repository.ConsoleRepository;
import com.company.gamestore.repository.TShirtRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(ConsoleController.class)
class ConsoleControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();
    @MockBean
    private ConsoleRepository consoleRepository;
    private Console console;


}