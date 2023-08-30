package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.model.TShirt;
import com.company.gamestore.repository.GameRepository;
import com.company.gamestore.repository.TShirtRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(GameController.class)
class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();
    @MockBean
    private GameRepository gameRepository;
    private Game game;


}