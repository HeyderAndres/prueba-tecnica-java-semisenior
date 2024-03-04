package com.puntored.movies.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Schema(name = "FilmRequestDTO", description = "request para operaciones relacionada con una película")
public class FilmRequestDTO {
    @NotBlank
    @Schema(description = "Nombre de la película", example = "Jhon Wick")
    private String title;

    @NotBlank
    @Schema(description = "Sinopsis de la película", example = "La franquicia esta centrada en John Wick, " +
            "un ex asesino a sueldo que se ve obligado a regresar al inframundo criminal que" +
            " había abandonado anteriormente.")
    private String description;

    @NotBlank
    @Schema(description = "año de lanzamiento de la película", example = "2014")
    @Pattern(regexp = "^[0-9]*$", message = "solo se permiten valores numericos")
    private String year;

    @NotNull
    @Positive
    @Schema(description = "Tiempo de renta de la película en horas", example = "24")
    private Float rentalDuration;

    @NotNull
    @Positive
    @Schema(description = "Calificación general de la película", example = "9.0")
    @Digits(fraction = 1, integer = 2)
    private Float rating;

    @NotNull
    @Positive
    @Digits(fraction = 2, integer = 2)
    @Schema(description = "Tiempo de duración de la película en minutos", example = "120")
    private Float duration;

    @NotNull
    @Positive
    @Digits(fraction = 2, integer = 16)
    @Schema(description = "Precio de renta de la película", example = "15000.00")
    private Float rentalPrice;

    @NotNull
    @Positive
    @Schema(description = "Identificador único de la categoría", example = "15000.00")
    private long categoryId;
}
