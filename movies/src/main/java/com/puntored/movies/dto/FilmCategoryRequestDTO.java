package com.puntored.movies.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class FilmCategoryRequestDTO {
    @NotNull
    @Positive
    private long filmCategoryId;

    @NotNull
    @Positive
    private long filmId;

    @NotNull
    @Positive
    private long categoryId;
}
