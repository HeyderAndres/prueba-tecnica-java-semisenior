/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.puntored.movies.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 *
 * @author heiderarellano
 */
@Entity
@Data
@Schema(name = "Category", description = "Género al cual pertenece una película")
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único de la categoría", example = "1")
    @Column(name = "category_id")
    private long categoryId;

    @Schema(description = "Nombre de la categoría", example = "Acción")
    private String name;

    @Schema(description = "Descripción de la categoría", example = "Contenido que incluye violencia")
    private String description;
}
