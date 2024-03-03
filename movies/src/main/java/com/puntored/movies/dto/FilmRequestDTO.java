package com.puntored.movies.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class FilmRequestDTO {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "solo se permiten valores alfanumericos")
    private String title;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "solo se permiten valores alfanumericos")
    private String description;

    @NotBlank
    @Pattern(regexp = "^[0-9]*$", message = "solo se permiten valores numericos")
    private String year;

    @NotNull
    @Positive
    private Float rentalDuration;

    @NotNull
    @Positive
    @Digits(fraction = 1, integer = 2)
    private Float rating;

    @NotNull
    @Positive
    @Digits(fraction = 2, integer = 2)
    private Float duration;

    @NotNull
    @Positive
    @Digits(fraction = 2, integer = 16)
    private Float rentalPrice;

    @NotNull
    @Positive
    private long categoryId;
}
