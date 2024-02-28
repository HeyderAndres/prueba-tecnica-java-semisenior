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
public class FilmCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long filmcaterogy_id;
    private long film_id;
    private long category_id;
}
