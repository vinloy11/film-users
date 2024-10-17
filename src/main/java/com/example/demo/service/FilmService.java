package com.example.demo.service;

import com.example.demo.dao.FilmDao;
import com.example.demo.model.AddFilmRequestBody;
import com.example.demo.model.Film;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FilmService {
    @Getter
    @Setter
    private int currentId = 0;
    HashMap<Integer, Film> films = new HashMap<>();
    private final FilmDao filmDao;

    public Optional<Film> getFilmById(int id) {
        return this.filmDao.findFilmById(id);
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
                .id(this.getCurrentId())
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

