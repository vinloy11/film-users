package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.model.AddUserRequestBody;
import com.example.demo.model.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    @Getter
    @Setter
    private int currentId = 0;
    HashMap<Integer, User> users = new HashMap<>();
    private final UserDao userDao;

    public Optional<User> getUserById(int id) {
        return this.userDao.findUserById(id);
    }

    public List<User> getAllUsers() {
        return this.userDao.getUsers();
    }

    public User addUser(AddUserRequestBody user) {
        User newUser = User.builder()
                .name(user.getName())
                .email(user.getEmail())
                .login(user.getLogin())
                .birthday(user.getBirthday())
                .id(this.getCurrentId())
                .build();

        users.put(newUser.getId(), newUser);

        this.setCurrentId(this.getCurrentId() + 1);

        return newUser;
    }

    public User updateUser(User User) {
        users.put(User.getId(), User);
        return User;
    }
}
