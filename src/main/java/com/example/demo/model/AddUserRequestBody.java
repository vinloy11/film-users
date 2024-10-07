package com.example.demo.model;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AddUserRequestBody extends AbstractUser {
    public AddUserRequestBody(int id, String email, String login, String name, LocalDate birthday) {
        super(id, email, login, name, birthday);
    }
}
