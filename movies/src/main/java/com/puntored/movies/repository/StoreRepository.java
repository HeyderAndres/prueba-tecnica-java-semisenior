/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.puntored.movies.repository;

import com.puntored.movies.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author heiderarellano
 */
public interface StoreRepository extends JpaRepository<Store, Long> {
    
}
