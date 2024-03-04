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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Películas",
        description = "Endpoints para realizar CRUD de película")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @GetMapping()
    @Operation(summary = "Buscar Películas",
            description = "Retorna las películas disponibles")
    public ApiResponseDTO<List<Film>> findAll() {
        return filmService.findAll();

    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar película por id",
            description = "Retorna una película que tenga el id suministrado")
    public ApiResponseDTO<Film> finById(@PathVariable long id) {
        return filmService.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar película",
            description = "Recibe un id y un Objeto FilmRequestDTO" +
                    " para actualizar y devuelve un Objeto ApiResponseDTO")
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
    @Operation(summary = "Crear película",
            description = "Recibe un Objeto FilmRequestDTO" +
                    " para crear y devuelve un Objeto ApiResponseDTO")
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
    @Operation(summary = "Eliminar Película",
            description = "Recibe un id de una película," +
                    " devuelve un Objeto ApiResponseDTO")
    public ResponseEntity<ApiResponseDTO<Film>> delete(@PathVariable long id) {
        ApiResponseDTO<Film> response = filmService.delete(id);
        if (!response.getCode().equals("200")) {
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/stores/{id}")
    @Operation(summary = "Buscar disponibilidad de una película",
            description = "Retorna un Objeto ApiResponseDTO con un array de tiendas y" +
                    " la cantidad de películas por tienda")
    public ApiResponseDTO<List<MoviesStoreDTO>> findStoresAndQuantityByFilmId(@PathVariable long id){
        return filmService.findStoresAndQuantityByFilmId(id);
    }

}
