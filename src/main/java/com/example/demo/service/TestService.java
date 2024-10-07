package com.example.demo.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Getter
@Setter
@Service
public class TestService {
    HashMap<String, String> map = new HashMap<>();
    private int age = 10;

    public String addItem(String key, String value) {
        this.map.put(key, value);

        return key;
    }

    public String hello() {
        return "hello ";
    }
}
