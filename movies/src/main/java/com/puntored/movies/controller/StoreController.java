/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.puntored.movies.controller;

import com.puntored.movies.dto.ApiResponseDTO;
import com.puntored.movies.dto.StoreRequestDTO;
import com.puntored.movies.entity.Store;
import com.puntored.movies.service.StoreService;
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
@RequestMapping("/store")
@Tag(name = "Tienda",
        description = "Endpoints para realizar CRUD de tiendas de película")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping()
    @Operation(summary = "Buscar tiendas",
            description = "Retorna los tiendas disponibles")
    public ApiResponseDTO<List<Store>> findAll() {
        return storeService.findAll();

    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar tienda por id",
            description = "Retorna el tienda que tenga el id suministrado")
    public ApiResponseDTO<Store> finById(@PathVariable long id) {
        return storeService.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar tienda",
            description = "Recibe un id y un Objeto StoreRequestDTO" +
                    " para actualizar y devuelve un Objeto ApiResponseDTO")
    public ResponseEntity<ApiResponseDTO<Store>> update(
            @Valid @RequestBody StoreRequestDTO request, BindingResult result, @PathVariable long id) {
        ApiResponseDTO<Store> response = new ApiResponseDTO<>();

        if (result.hasErrors()){
            response.setFailRequestParams();
            System.out.println(result.getAllErrors());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        response = storeService.update(id,request);
        if (!response.getCode().equals("200")) {
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Crear tienda",
            description = "Recibe un Objeto StoreyRequestDTO" +
                    " para crear y devuelve un Objeto ApiResponseDTO")
    public ResponseEntity<ApiResponseDTO<Store>> create(@Valid @RequestBody StoreRequestDTO request, BindingResult result) {
        ApiResponseDTO<Store> response = new ApiResponseDTO<>();
        if (result.hasErrors()){
            response.setFailRequestParams();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        response = storeService.create(request);
        if (!response.getCode().equals("200")) {
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Tienda",
            description = "Recibe un id de tienda," +
                    " devuelve un Objeto ApiResponseDTO")
    public ResponseEntity<ApiResponseDTO<Store>> delete(@PathVariable long id) {
        ApiResponseDTO<Store> response = storeService.delete(id);
        if (!response.getCode().equals("200")){
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
