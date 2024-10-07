package com.example.demo.model;

import com.example.demo.validator.IsAfter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class AbstractFilm {
    @NotBlank(message = "Name is mandatory")
    private String name;
    @Size.List({
            @Size(min = 2, message = "{validation.description.size.too_short}"),
            @Size(max = 200, message = "{validation.description.size.too_long}")
    })
    @NotBlank(message = "description is mandatory")
    private String description;

    @IsAfter(current = "1895-12-28")
    @DateTimeFormat( pattern="yyyy-MM-dd")
    private LocalDate releaseDate;
    /**
     * Продолжительность в минутах
     */
    @Positive
    private int duration;
}
