package com.example.demo.controller;

import com.example.demo.model.AddUserRequestBody;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public Optional<User> getById(@Valid @PathVariable int id) {
        return this.userService.getUserById(id);
    }

    @GetMapping
    public List<User> getUsers() {
        return this.userService.getAllUsers();
    }

    @PostMapping
    public User create(@Valid @RequestBody AddUserRequestBody user) {
        return this.userService.addUser(user);
    }

    @PutMapping
    public User update(@Valid @RequestBody User user) {
        return this.userService.updateUser(user);
    }
}
