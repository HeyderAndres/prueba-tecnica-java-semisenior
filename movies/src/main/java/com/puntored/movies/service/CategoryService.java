package com.puntored.movies.service;

import com.puntored.movies.dto.ApiResponseDTO;
import com.puntored.movies.dto.CategoryRequestDTO;
import com.puntored.movies.entity.Category;
import com.puntored.movies.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public ApiResponseDTO<List<Category>> findAll() {
        ApiResponseDTO<List<Category>> response = new ApiResponseDTO<>();
        try {
            List<Category> dataResponse = categoryRepository.findAll();
            if (dataResponse.isEmpty()) {
                response.setFailQuery("No se encontraron resultados");
                return response;
            }
            response.setSuccesQuery(dataResponse);

        } catch (Exception e) {
            response.setFailService();
            System.out.println("Error: " + e.getMessage());
        }
        return response;
    }

    public ApiResponseDTO<Category> findById(long id) {
        ApiResponseDTO<Category> response = new ApiResponseDTO<>();
        try {
            Optional<Category> dataResponse = categoryRepository.findById(id);
            if (dataResponse.isEmpty()) {
                response.setFailQuery("No se encontraron resultados");
                return response;
            }
            response.setSuccesQuery(dataResponse.get());

        } catch (Exception e) {
            response.setFailService();
            System.out.println("Error: " + e.getMessage());
        }
        return response;
    }

    public ApiResponseDTO<Category> update(long id, CategoryRequestDTO request) {
        ApiResponseDTO<Category> response = new ApiResponseDTO<>();
        try {
            Category dataResponse =  categoryRepository.findById(id).orElse(null);
            if (Objects.isNull(dataResponse)) {
                response.setFailQuery("No se encontraron resultados");
                return response;
            }

            dataResponse.setName(request.getName());
            dataResponse.setDescription(request.getDescription());
            categoryRepository.save(dataResponse);
            response.setSuccesQuery(dataResponse);

        } catch (Exception e) {
            response.setFailService();
            System.out.println("Error: " + e.getMessage());
        }
        return response;
    }

    public ApiResponseDTO<Category> create(CategoryRequestDTO request) {
        ApiResponseDTO<Category> response = new ApiResponseDTO<>();
        try {

            Category newCategory = new Category();
            newCategory.setName(request.getName());
            newCategory.setDescription(request.getDescription());
            Category category = categoryRepository.save(newCategory);
            System.out.println(category);

            if (categoryRepository.findById(category.getCategoryId()).isEmpty()){
                response.setDeclinedTrasaction(category);
                return response;
            }

            response.setSuccesQuery(category);

        } catch (Exception e) {
            response.setFailService();
            System.out.println("Error: " + e.getMessage());
        }
        return response;
    }

    public ApiResponseDTO<Category> delete(long id){
        ApiResponseDTO<Category> response = new ApiResponseDTO<>();
        try {
            Category category = categoryRepository.findById(id).orElse(null);
            if (Objects.isNull(category)){
                response.setDeclinedTrasaction(null);
                return response;
            }
            categoryRepository.deleteById(id);
            response.setSuccesTrasaction(category);
        }catch (Exception e){
            response.setFailService();
            System.out.println("Error: " + e.getMessage());
        }
        return response;
    }

}
