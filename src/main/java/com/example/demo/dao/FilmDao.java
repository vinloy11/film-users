package com.example.demo.dao;


import com.example.demo.model.Film;

import java.util.Optional;

public interface FilmDao {
    Optional<Film> findFilmById(int id);
}
