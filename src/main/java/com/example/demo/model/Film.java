package com.example.demo.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Film extends AbstractFilm {

    private int id;

    @Builder
    public Film(String name, String description, LocalDate releaseDate, int duration, int id) {
        super(name, description, releaseDate, duration);

        this.id = id;
    }
}
