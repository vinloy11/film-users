package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleIncorrectParameterException(final MethodArgumentNotValidException e) {
        return Map.of("error", e.getMessage());
    }

//    @ExceptionHandler({UserNotFoundException.class, PostNotFoundException.class})
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public Map<String, String> handleUserNotFoundException(final RuntimeException e) {
//        return Map.of("error", e.getMessage());
//    }
//
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.CONFLICT)
//    public Map<String, String> handleUserAlreadyExistException(final UserAlreadyExistException e) {
//        return Map.of("error", e.getMessage());
//    }
//
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Map<String, String> handleInvalidEmailException(final InvalidEmailException e) {
//        return Map.of("error", e.getMessage());
//    }
//
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Map<String, String> handleIncorrectParameterException(final IncorrectParameterException e) {
//        return Map.of("error", "Ошибка с полем " + e.getMessage());
//    }
//
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public Map<String, String> handleThrowableException(final Throwable e) {
//        return Map.of("error", "Произошла непредвиденная ошибка.");
//    }
}
