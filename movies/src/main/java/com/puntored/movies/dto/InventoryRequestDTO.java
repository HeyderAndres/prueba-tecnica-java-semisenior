package com.puntored.movies.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class InventoryRequestDTO {
    @NotNull
    @Positive
    private long filmId;

    @NotNull
    @Positive
    private long storeId;

    @NotNull
    @Positive
    private long categoryId;

    @NotNull
    @Positive
    private int quantity;
}
