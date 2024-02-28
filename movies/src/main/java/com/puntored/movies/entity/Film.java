/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.puntored.movies.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 *
 * @author heiderarellano
 */
@Entity
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long film_id;
    private String title;
    private String description;
    private String year;
    private long rentalDuration;
    private long rating;
    private long duration;
    private long rental_price;
}
