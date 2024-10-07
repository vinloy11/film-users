package com.example.demo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class User extends AbstractUser {
    private int id;

    @Builder
    public User(String email, String login, String name, LocalDate birthday, int id) {
        super(id, email, login, name, birthday);

        this.id = id;
    }
}
