package com.puntored.movies.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "StoreRequestDTO", description = "request para operaciones relacionada con una tienda")
public class StoreRequestDTO {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "solo se permiten valores alfanumericos")
    @Schema(description = "Dirección de la tienda")
    private String address;
}
