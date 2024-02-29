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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;

/**
 *
 * @author heiderarellano
 */
@Entity
@Getter
@Setter
@Table(name = "film_category")
public class FilmCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "filmcategory_id")
    private long filmcaterogyId;
    @ManyToAny
    @JoinColumn(name = "film_id")
    private Film film;
    
    @ManyToAny
    @JoinColumn(name = "category_id")
    private Category category;
}
