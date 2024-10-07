package com.example.demo.controller;

import com.example.demo.model.AddFilmRequestBody;
import com.example.demo.model.Film;
import com.example.demo.service.FilmService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/films")
@RequiredArgsConstructor
public class FilmController {
    private final FilmService filmService;

    @GetMapping("/{id}")
    public Film getById(@Valid @PathVariable int id) {
        return this.filmService.getFilmById(id);
    }

    @GetMapping
    public List<Film> getFilms() {
        return this.filmService.getAllFilms();
    }

    @PostMapping
    public Film create(@Valid @RequestBody AddFilmRequestBody film) {
        return this.filmService.addFilm(film);
    }

    @PutMapping
    public Film update(@Valid @RequestBody Film film) {
        return this.filmService.updateFilm(film);
    }
}

