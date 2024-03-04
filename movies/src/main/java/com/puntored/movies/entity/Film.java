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
@Schema(name = "Film", description = "Entidad para almacenar las películas")
@Table(name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único de la película")
    @Column(name = "film_id")
    private long filmId;

    @Schema(description = "Nombre de la película", example = "Jhon Wick")
    private String title;

    @Schema(description = "Sinopsis de la película", example = "La franquicia esta centrada en John Wick, " +
            "un ex asesino a sueldo que se ve obligado a regresar al inframundo criminal que había abandonado anteriormente.")
    private String description;

    @Schema(description = "año de lanzamiento de la película", example = "2014")
    @Column(name = "year_movie")
    private String yearMovie;

    @Schema(description = "Tiempo de renta de la película en horas", example = "24")
    @Column(name = "rental_duration")
    private Float rentalDuration;

    @Schema(description = "Calificación general de la película", example = "9.0")
    private Float rating;

    @Schema(description = "Tiempo de duración de la película en minutos", example = "120")
    private Float duration;

    @Schema(description = "Precio de renta de la película", example = "15000.00")
    @Column(name = "rental_price")
    private Float rentalPrice;
}
