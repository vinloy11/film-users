package com.example.demo.controller;

import com.example.demo.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class TestController {
    private final TestService testService;

    @GetMapping()
    public String hello() {
        log.info("hello");
        log.error("error hello");
        return this.testService.hello();
//        throw new CustomException("hello");
//        return "Hello World";
    }

    @PutMapping()
    public void put(@RequestBody String body) {
        this.testService.addItem(body, body);
    }
}
