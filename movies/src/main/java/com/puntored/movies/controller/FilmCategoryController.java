/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.puntored.movies.controller;

import com.puntored.movies.dto.ApiResponseDTO;
import com.puntored.movies.dto.FilmCategoryRequestDTO;
import com.puntored.movies.dto.InventoryRequestDTO;
import com.puntored.movies.entity.FilmCategory;
import com.puntored.movies.entity.Inventory;
import com.puntored.movies.service.FilmCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author heiderarellano
 */
@RestController
@Tag(name = "Peliculas - Categoría",
        description = "Endpoints para realizar CRUD de la relación entre peliculas y sus géneros")
@RequestMapping("/filmcategory")
public class FilmCategoryController {

    @Autowired
    private FilmCategoryService filmCategoryService;

    @GetMapping()
    @Operation(summary = "Buscar relaciones Pelicula - Categoría",
            description = "Retorna las realaciones entre peliculas y categorías disponibles")
    public ApiResponseDTO<List<FilmCategory>> findAll() {
        return filmCategoryService.findAll();

    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Categoría por id", description = "Retorna una categoría que tenga el id suministrado")
    public ApiResponseDTO<FilmCategory> finById(@PathVariable long id) {
        return filmCategoryService.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Relación película - categoría",
            description = "Recibe un id de relación y un Objeto FilmCategoryRequestDTO" +
                    " para actualizar y devuelve un Objeto ApiResponseDTO")
    public ResponseEntity<ApiResponseDTO<FilmCategory>> update(
            @Valid @RequestBody FilmCategoryRequestDTO request, BindingResult result, @PathVariable long id) {
        ApiResponseDTO<FilmCategory> response = new ApiResponseDTO<>();

        if (result.hasErrors()) {
            response.setFailRequestParams();
            System.out.println(result.getAllErrors());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        response = filmCategoryService.update(id, request);
        if (!response.getCode().equals("200")) {
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Crear Relación película - categoría",
            description = "Recibe un Objeto FilmCategoryRequestDTO" +
                    " para acrar y devuelve un Objeto ApiResponseDTO")
    public ResponseEntity<ApiResponseDTO<FilmCategory>> create(@Valid @RequestBody FilmCategoryRequestDTO request, BindingResult result) {
        ApiResponseDTO<FilmCategory> response = new ApiResponseDTO<>();
        if (result.hasErrors()) {
            response.setFailRequestParams();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        response = filmCategoryService.create(request);
        if (!response.getCode().equals("200")) {
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Relación película - categoría",
            description = "Recibe un id de una Relación película - categoría," +
                    " devuelve un Objeto ApiResponseDTO")
    public ResponseEntity<ApiResponseDTO<FilmCategory>> delete(@PathVariable long id) {
        ApiResponseDTO<FilmCategory> response = filmCategoryService.delete(id);
        if (!response.getCode().equals("200")) {
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
