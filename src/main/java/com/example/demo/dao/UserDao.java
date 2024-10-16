package com.example.demo.dao;

import com.example.demo.model.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> findUserById(int id);
}

