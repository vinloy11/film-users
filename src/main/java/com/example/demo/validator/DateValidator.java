package com.example.demo.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class DateValidator implements ConstraintValidator<IsAfter, LocalDate> {

    String validDate;

    @Override
    public void initialize(IsAfter constraintAnnotation) {
        validDate = constraintAnnotation.current();
    }

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {
        String[] splitDate = validDate.split("-");
        System.out.println("date " + date);
        System.out.println("splitDate " + splitDate[0] + splitDate[1] + splitDate[2]);
        System.out.println(date.isAfter(LocalDate.of(Integer.parseInt(splitDate[0]), Integer.parseInt(splitDate[1]), Integer.parseInt(splitDate[2]))));
        return date.isAfter(LocalDate.of(Integer.parseInt(splitDate[0]), Integer.parseInt(splitDate[1]), Integer.parseInt(splitDate[2])));
    }
}