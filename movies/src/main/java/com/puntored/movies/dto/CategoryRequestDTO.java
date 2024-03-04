package com.puntored.movies.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "CategoryRequestDTO", description = "request para operaciones relacionada con categoría")
public class CategoryRequestDTO {

    @Schema(description = "Nombre de la categoría", example = "Acción")
    @NotBlank
    private String name;

    @Schema(description = "Descripción de la categoría", example = "Contenido que incluye violencia")
    @NotBlank
    private String description;

}
