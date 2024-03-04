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
 *
 * @author heiderarellano
 */
@RestController
@RequestMapping("/filmcategory")
public class FilmCategoryController {

    @Autowired
    private FilmCategoryService filmCategoryService;

    @GetMapping()
    public ApiResponseDTO<List<FilmCategory>> findAll() {
        return filmCategoryService.findAll();

    }

    @GetMapping("/{id}")
    public ApiResponseDTO<FilmCategory> finById(@PathVariable long id) {
        return filmCategoryService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<FilmCategory>> update(
            @Valid @RequestBody FilmCategoryRequestDTO request, BindingResult result, @PathVariable long id) {
        ApiResponseDTO<FilmCategory> response = new ApiResponseDTO<>();

        if (result.hasErrors()){
            response.setFailRequestParams();
            System.out.println(result.getAllErrors());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        response = filmCategoryService.update(id,request);
        if (!response.getCode().equals("200")) {
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO<FilmCategory>> create(@Valid @RequestBody FilmCategoryRequestDTO request, BindingResult result) {
        ApiResponseDTO<FilmCategory> response = new ApiResponseDTO<>();
        if (result.hasErrors()){
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
    public ResponseEntity<ApiResponseDTO<FilmCategory>> delete(@PathVariable long id) {
        ApiResponseDTO<FilmCategory> response = filmCategoryService.delete(id);
        if (!response.getCode().equals("200")){
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
