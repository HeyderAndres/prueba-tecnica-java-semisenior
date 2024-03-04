/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.puntored.movies.controller;

import com.puntored.movies.dto.ApiResponseDTO;
import com.puntored.movies.dto.CategoryRequestDTO;
import com.puntored.movies.dto.InventoryRequestDTO;
import com.puntored.movies.entity.Category;
import com.puntored.movies.entity.Inventory;
import com.puntored.movies.service.InventoryService;
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
@RequestMapping("/inventory")
@Tag(name = "Inventario",
        description = "Endpoints para realizar CRUD de inventario de película")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping()
    @Operation(summary = "Buscar inventario",
            description = "Retorna los inventario disponibles")
    public ApiResponseDTO<List<Inventory>> findAll() {
        return inventoryService.findAll();

    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Inventario por id",
            description = "Retorna el inventario que tenga el id suministrado")
    public ApiResponseDTO<Inventory> finById(@PathVariable long id) {
        return inventoryService.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar inventario",
            description = "Recibe un id y un Objeto InventoryRequestDTO" +
                    " para actualizar y devuelve un Objeto ApiResponseDTO")
    public ResponseEntity<ApiResponseDTO<Inventory>> update(
            @Valid @RequestBody InventoryRequestDTO request, BindingResult result, @PathVariable long id) {
        ApiResponseDTO<Inventory> response = new ApiResponseDTO<>();

        if (result.hasErrors()){
            response.setFailRequestParams();
            System.out.println(result.getAllErrors());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        response = inventoryService.update(id,request);
        if (!response.getCode().equals("200")) {
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Crear Inventario",
            description = "Recibe un Objeto InventoryRequestDTO" +
                    " para crear y devuelve un Objeto ApiResponseDTO")
    public ResponseEntity<ApiResponseDTO<Inventory>> create(@Valid @RequestBody InventoryRequestDTO request, BindingResult result) {
        ApiResponseDTO<Inventory> response = new ApiResponseDTO<>();
        if (result.hasErrors()){
            response.setFailRequestParams();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        response = inventoryService.create(request);
        if (!response.getCode().equals("200")) {
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar inventario",
            description = "Recibe un id de inventario," +
                    " devuelve un Objeto ApiResponseDTO")
    public ResponseEntity<ApiResponseDTO<Inventory>> delete(@PathVariable long id) {
        ApiResponseDTO<Inventory> response = inventoryService.delete(id);
        if (!response.getCode().equals("200")){
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
