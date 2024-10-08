package com.example.demo.comtroller;

import com.example.demo.controller.FilmController;
import com.example.demo.model.AddFilmRequestBody;
import com.example.demo.model.Film;
import com.example.demo.service.FilmService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FilmController.class)
public class FilmTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private FilmService filmService;

    @Test
    void getById() throws Exception {
        Film film = Film.builder().name("Перевозчик").build();
        given(this.filmService.getFilmById(2))
                .willReturn(film);
        this.mvc.perform(get("/films/2").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Перевозчик"));
    }

    @Test
    void create() throws Exception {
        Film response = Film.builder().id(0).name("Перевозчик").build();
        given(filmService.addFilm(any(AddFilmRequestBody.class))).willReturn(response);


        this.mvc.perform(
                        post("/films")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "  \"name\": \"Перевозчик\",\n" +
                                        "  \"description\": \"description\",\n" +
                                        "  \"releaseDate\": \"2024-10-08\",\n" +
                                        "  \"duration\": 200\n" +
                                        "}")
                                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(0))
                .andExpect(jsonPath("$.name").value("Перевозчик"));
    }

    @Test
    void createUncorrectedDate() throws Exception {
        Film response = Film.builder().id(0).name("Перевозчик").build();
        given(filmService.addFilm(any(AddFilmRequestBody.class))).willReturn(response);


        this.mvc.perform(
                        post("/films")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "  \"name\": \"Перевозчик\",\n" +
                                        "  \"description\": \"description\",\n" +
                                        "  \"releaseDate\": \"1894-12-28\",\n" +
                                        "  \"duration\": 200\n" +
                                        "}")
                                .characterEncoding("utf-8"))
                .andExpect(status().is(400));
    }

    @Test
    void update() throws Exception {
        Film response = Film.builder().id(22).name("Перевозчик").build();
        given(filmService.updateFilm(any(Film.class))).willReturn(response);


        this.mvc.perform(
                        put("/films")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "  \"id\": 22,\n" +
                                        "  \"name\": \"Перевозчик\",\n" +
                                        "  \"description\": \"description\",\n" +
                                        "  \"releaseDate\": \"2024-10-08\",\n" +
                                        "  \"duration\": 200\n" +
                                        "}")
                                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(22))
                .andExpect(jsonPath("$.name").value("Перевозчик"));
    }
}
