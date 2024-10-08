package com.example.demo.model;

import lombok.Builder;

import java.time.LocalDate;

public class AddFilmRequestBody extends AbstractFilm {
    @Builder
    public AddFilmRequestBody(String name, String description, LocalDate releaseDate, int duration) {
        super(name, description, releaseDate, duration);
    }
}
