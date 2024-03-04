package com.puntored.movies.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Schema(name = "FilmCategoryRequestDTO", description = "request para operaciones relacionada película y su género")
public class FilmCategoryRequestDTO {

    @Schema(description = "Identificador único de la película", example = "1")
    @NotNull
    @Positive
    private long filmId;

    @Schema(description = "Identificador de la categoría", example = "1")
    @NotNull
    @Positive
    private long categoryId;
}
