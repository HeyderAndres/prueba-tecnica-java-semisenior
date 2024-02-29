/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.puntored.movies.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author heiderarellano
 */
@Entity
@Getter
@Setter
@Table(name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "film_id")
    private long filmId;
    private String title;
    private String description;
    private String year;
    @Column(name = "rental_duration")
    private long rentalDuration;
    private long rating;
    private long duration;
    @Column(name = "rental_price")
    private long rentalPPrice;
}
