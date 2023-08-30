package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.model.TShirt;
import com.company.gamestore.repository.GameRepository;
import com.company.gamestore.repository.TShirtRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(GameController.class)
class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();
    @MockBean
    private GameRepository gameRepository;
    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
        game.setTitle("The Legend of Adventure");
        game.setEsrbRating("E10+");
        game.setDescription("Embark on an epic journey to save the mystical land from darkness.");
        game.setPrice(49.99);
        game.setStudio("QuestWorks Studios");
        game.setQuantity(100);

    }
    @Test
    public void shouldCreateNewGame() throws Exception {
        when(gameRepository.save(any(Game.class))).thenReturn(game);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/games")
                        .content(mapper.writeValueAsString(game))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("The Legend of Adventure"))
                .andExpect(jsonPath("$.esrbRating").value("E10+"))
                .andExpect(jsonPath("$.description").value("Embark on an epic journey to save the mystical land from darkness."))
                .andExpect(jsonPath("$.price").value(49.99))
                .andExpect(jsonPath("$.studio").value("QuestWorks Studios"))
                .andExpect(jsonPath("$.quantity").value(100));
    }

    @Test
    public void shouldGetAllGames() throws Exception {
        when(gameRepository.findAll()).thenReturn(Arrays.asList(game));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/games"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("The Legend of Adventure"))
                .andExpect(jsonPath("$[0].esrbRating").value("E10+"))
                .andExpect(jsonPath("$[0].description").value("Embark on an epic journey to save the mystical land from darkness."))
                .andExpect(jsonPath("$[0].price").value(49.99))
                .andExpect(jsonPath("$[0].studio").value("QuestWorks Studios"))
                .andExpect(jsonPath("$[0].quantity").value(100));
    }

    @Test
    public void shouldGetGameById() throws Exception {
        when(gameRepository.findById(1)).thenReturn(Optional.of(game));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/games/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("The Legend of Adventure"))
                .andExpect(jsonPath("$.esrbRating").value("E10+"))
                .andExpect(jsonPath("$.description").value("Embark on an epic journey to save the mystical land from darkness."))
                .andExpect(jsonPath("$.price").value(49.99))
                .andExpect(jsonPath("$.studio").value("QuestWorks Studios"))
                .andExpect(jsonPath("$.quantity").value(100));

    }

    @Test
    public void shouldUpdateGame() throws Exception {
        when(gameRepository.save(any(Game.class))).thenReturn(game);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/games/{id}", game.getGameId())
                        .content(mapper.writeValueAsString(game))
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(status().isNoContent());
    }
    @Test
    public void shouldDeleteGameById() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/games/{id}", 1)

        ).andExpect(status().isNoContent());
    }

    @Test
    public void shouldGetGameByStudio() throws Exception {
        when(gameRepository.findByStudio("QuestWorks Studios")).thenReturn(Arrays.asList(game));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/games/studio/{studio}", "QuestWorks Studios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("The Legend of Adventure"))
                .andExpect(jsonPath("$[0].esrbRating").value("E10+"))
                .andExpect(jsonPath("$[0].description").value("Embark on an epic journey to save the mystical land from darkness."))
                .andExpect(jsonPath("$[0].price").value(49.99))
                .andExpect(jsonPath("$[0].studio").value("QuestWorks Studios"))
                .andExpect(jsonPath("$[0].quantity").value(100));
    }


    @Test
    public void shouldGetGameByTitle() throws Exception {
        when(gameRepository.findByTitle("The Legend of Adventure")).thenReturn(Arrays.asList(game));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/games/title/{title}", "The Legend of Adventure"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("The Legend of Adventure"))
                .andExpect(jsonPath("$[0].esrbRating").value("E10+"))
                .andExpect(jsonPath("$[0].description").value("Embark on an epic journey to save the mystical land from darkness."))
                .andExpect(jsonPath("$[0].price").value(49.99))
                .andExpect(jsonPath("$[0].studio").value("QuestWorks Studios"))
                .andExpect(jsonPath("$[0].quantity").value(100));
    }

    @Test
    public void shouldGetGameByEsrbRating() throws Exception {
        when(gameRepository.findByEsrbRating("E10+")).thenReturn(Arrays.asList(game));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/games/esrbRating/{esrbRating}", "E10+"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("The Legend of Adventure"))
                .andExpect(jsonPath("$[0].esrbRating").value("E10+"))
                .andExpect(jsonPath("$[0].description").value("Embark on an epic journey to save the mystical land from darkness."))
                .andExpect(jsonPath("$[0].price").value(49.99))
                .andExpect(jsonPath("$[0].studio").value("QuestWorks Studios"))
                .andExpect(jsonPath("$[0].quantity").value(100));
    }





}