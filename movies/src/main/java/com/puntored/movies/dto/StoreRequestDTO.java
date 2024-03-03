package com.puntored.movies.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class StoreRequestDTO {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "solo se permiten valores alfanumericos")
    private String address;
}
