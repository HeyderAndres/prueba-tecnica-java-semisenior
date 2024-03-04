package com.puntored.movies.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "MoviesStoreDTO", description = "Inventario de una película por tienda")
public class MoviesStoreDTO {
    @Schema(description = "Dirección de la tienda")
    private String address;

    @Schema(description = "Cantidad de unidades de una película")
    private int quantity;

}
