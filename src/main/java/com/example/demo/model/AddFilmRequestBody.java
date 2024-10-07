package com.example.demo.model;

import java.time.LocalDate;

public class AddFilmRequestBody extends AbstractFilm {
    public AddFilmRequestBody(String name, String description, LocalDate releaseDate, int duration) {
        super(name, description, releaseDate, duration);
    }
}
