package com.puntored.movies.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CategoryRequestDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

}
