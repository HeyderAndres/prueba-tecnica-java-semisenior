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
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "store_id")
    private long storeId;
    private String address;
}
