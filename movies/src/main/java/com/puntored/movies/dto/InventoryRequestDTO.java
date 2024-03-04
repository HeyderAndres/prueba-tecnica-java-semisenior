package com.puntored.movies.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Schema(name = "InventoryRequestDTO", description = "request para operaciones relacionada con inventario")
public class InventoryRequestDTO {
    @NotNull
    @Positive
    @Schema(description = "identificador único de la película")
    private long filmId;

    @NotNull
    @Positive
    @Schema(description = "Identificador único de la tienda")
    private long storeId;

    @NotNull
    @Positive
    @Schema(description = "Cantidad de películas disponible para renta")
    private int quantity;
}
