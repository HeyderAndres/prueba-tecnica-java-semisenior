/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.puntored.movies.controller;

import com.puntored.movies.dto.ApiResponseDTO;
import com.puntored.movies.dto.CategoryRequestDTO;
import com.puntored.movies.dto.FilmRequestDTO;
import com.puntored.movies.dto.MoviesStoreDTO;
import com.puntored.movies.entity.Category;
import com.puntored.movies.entity.Film;
import com.puntored.movies.service.FilmService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @author heiderarellano
 */
@RestController
@RequestMapping("/film")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @GetMapping()
    public ApiResponseDTO<List<Film>> findAll() {
        return filmService.findAll();

    }

    @GetMapping("/{id}")
    public ApiResponseDTO<Film> finById(@PathVariable long id) {
        return filmService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Film>> update(
            @Valid @RequestBody FilmRequestDTO request, BindingResult result, @PathVariable long id) {
        ApiResponseDTO<Film> response = new ApiResponseDTO<>();

        if (result.hasErrors()) {
            response.setFailRequestParams();
            System.out.println(result.getAllErrors());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        response = filmService.update(id, request);
        if (!response.getCode().equals("200")) {
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO<Film>> create(@Valid @RequestBody FilmRequestDTO request, BindingResult result) {
        ApiResponseDTO<Film> response = new ApiResponseDTO<>();
        if (result.hasErrors()) {
            response.setFailRequestParams();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        response = filmService.create(request);
        if (!response.getCode().equals("200")) {
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Film>> delete(@PathVariable long id) {
        ApiResponseDTO<Film> response = filmService.delete(id);
        if (!response.getCode().equals("200")) {
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/stores/{id}")
    public ApiResponseDTO<List<MoviesStoreDTO>> findStoresAndQuantityByFilmId(@PathVariable long id){
        return filmService.findStoresAndQuantityByFilmId(id);
    }

}
