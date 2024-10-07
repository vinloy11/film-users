package com.example.demo.service;

import com.example.demo.model.AddFilmRequestBody;
import com.example.demo.model.Film;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class FilmService {
    @Getter
    @Setter
    private int currentId = 0;
    HashMap<Integer, Film> films = new HashMap<>();

    public Film getFilmById(int id) {
        return films.getOrDefault(id, null);
    }

    public List<Film> getAllFilms() {
        return new ArrayList<>(films.values());
    }

    public Film addFilm(AddFilmRequestBody film) {
        Film newFilm = Film.builder()
                .description(film.getDescription())
                .duration(film.getDuration())
                .releaseDate(film.getReleaseDate())
                .name(film.getName())
                .build();
        films.put(newFilm.getId(), newFilm);

        this.setCurrentId(this.getCurrentId() + 1);

        return newFilm;
    }

    public Film updateFilm(Film film) {
        films.put(film.getId(), film);
        return film;
    }
}
