/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.puntored.movies.controller;

import com.puntored.movies.dto.ApiResponseDTO;
import com.puntored.movies.entity.Category;
import com.puntored.movies.dto.CategoryRequestDTO;
import com.puntored.movies.service.CategoryService;
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
 *
 * @author heiderarellano
 */
@RestController
@Tag(name = "Categorias",
        description = "Endpoints para realizar CRUD de categorías de película")
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping()
    @Operation(summary = "Buscar Categorías",
            description = "Retorna las categorías disponibles")
    public ApiResponseDTO<List<Category>> findAll() {
        return categoryService.findAll();

    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Categoría por id",
            description = "Retorna una categoría que tenga el id suministrado")
    public ApiResponseDTO<Category> finById(@PathVariable long id) {
        return categoryService.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar categoría",
            description = "Recibe un id y un Objeto CategoryRequestDTO" +
            " para actualizar y devuelve un Objeto ApiResponseDTO")
    public ResponseEntity<ApiResponseDTO<Category>> update(
       @Valid @RequestBody CategoryRequestDTO request, BindingResult result,@PathVariable long id) {
        ApiResponseDTO<Category> response = new ApiResponseDTO<>();

        if (result.hasErrors()){
            response.setFailRequestParams();
            System.out.println(result.getAllErrors());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        response = categoryService.update(id,request);
        if (!response.getCode().equals("200")) {
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PostMapping
    @Operation(summary = "Crear categoría",
            description = "Recibe un Objeto CategoryRequestDTO" +
            " para crear y devuelve un Objeto ApiResponseDTO")
    public ResponseEntity<ApiResponseDTO<Category>> create(@Valid @RequestBody CategoryRequestDTO request, BindingResult result) {
        ApiResponseDTO<Category> response = new ApiResponseDTO<>();
        if (result.hasErrors()){
            response.setFailRequestParams();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        response = categoryService.create(request);
        if (!response.getCode().equals("200")) {
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar categoría",
            description = "Recibe un id de una categoría," +
            " devuelve un Objeto ApiResponseDTO")
    public ResponseEntity<ApiResponseDTO<Category>> delete(@PathVariable long id) {
        ApiResponseDTO<Category> response = categoryService.delete(id);
        if (!response.getCode().equals("200")){
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
