package com.example.demo.service;

import com.example.demo.model.AddUserRequestBody;
import com.example.demo.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {
    @Getter
    @Setter
    private int currentId = 0;
    HashMap<Integer, User> users = new HashMap<>();

    public User getUserById(int id) {
        return users.getOrDefault(id, null);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
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
