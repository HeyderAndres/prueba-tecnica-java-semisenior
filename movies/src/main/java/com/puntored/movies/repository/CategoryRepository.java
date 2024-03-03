/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.puntored.movies.repository;

import com.puntored.movies.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author heiderarellano
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
