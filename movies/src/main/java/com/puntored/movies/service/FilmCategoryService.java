package com.puntored.movies.service;

import com.puntored.movies.dto.ApiResponseDTO;
import com.puntored.movies.dto.FilmCategoryRequestDTO;
import com.puntored.movies.entity.Category;
import com.puntored.movies.entity.Film;
import com.puntored.movies.entity.FilmCategory;
import com.puntored.movies.repository.CategoryRepository;
import com.puntored.movies.repository.FilmCategoryRepository;
import com.puntored.movies.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FilmCategoryService {

    @Autowired
    private FilmCategoryRepository filmCategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FilmRepository filmRepository;

    public ApiResponseDTO<List<FilmCategory>> findAll() {
        ApiResponseDTO<List<FilmCategory>> response = new ApiResponseDTO<>();
        try {
            List<FilmCategory> dataResponse = filmCategoryRepository.findAll();
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

    public ApiResponseDTO<FilmCategory> findById(long id) {
        ApiResponseDTO<FilmCategory> response = new ApiResponseDTO<>();
        try {
            Optional<FilmCategory> dataResponse = filmCategoryRepository.findById(id);
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

    public ApiResponseDTO<FilmCategory> update(long id, FilmCategoryRequestDTO request) {
        ApiResponseDTO<FilmCategory> response = new ApiResponseDTO<>();
        try {
            FilmCategory dataResponse =  filmCategoryRepository.findById(id).orElse(null);
            if (Objects.isNull(dataResponse)) {
                response.setFailQuery("No se encontraron resultados");
                return response;
            }

            Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);
            if (Objects.isNull(category)){
                response.setDeclinedTrasaction(null);
                return response;
            }

            Film film = filmRepository.findById(request.getFilmId()).orElse(null);
            if (Objects.isNull(film)){
                response.setDeclinedTrasaction(null);
                return response;
            }

            dataResponse.setCategory(category);
            dataResponse.setFilm(film);
            filmCategoryRepository.save(dataResponse);
            response.setSuccesQuery(dataResponse);

        } catch (Exception e) {
            response.setFailService();
            System.out.println("Error: " + e.getMessage());
        }
        return response;
    }

    public ApiResponseDTO<FilmCategory> create(FilmCategoryRequestDTO request) {
        ApiResponseDTO<FilmCategory> response = new ApiResponseDTO<>();
        try {

            Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);
            if (Objects.isNull(category)){
                response.setDeclinedTrasaction(null,"Valide que la categoría ingresada exista");
                return response;
            }

            Film film = filmRepository.findById(request.getFilmId()).orElse(null);
            if (Objects.isNull(film)){
                response.setDeclinedTrasaction(null,"Valide que la película ingresada exista");
                return response;
            }

            FilmCategory filmCategory = new FilmCategory();
            filmCategory.setCategory(category);
            filmCategory.setFilm(film);
            FilmCategory dataResponse = filmCategoryRepository.save(filmCategory);

            if (filmCategoryRepository.findById(dataResponse.getFilmcaterogyId()).isEmpty()){
                response.setDeclinedTrasaction(dataResponse);
                return response;
            }

            response.setSuccesQuery(dataResponse);

        } catch (Exception e) {
            response.setFailService();
            System.out.println("Error: " + e.getMessage());
        }
        return response;
    }

    public ApiResponseDTO<FilmCategory> delete(long id){
        ApiResponseDTO<FilmCategory> response = new ApiResponseDTO<>();
        try {
            FilmCategory filmCategory = filmCategoryRepository.findById(id).orElse(null);
            if (Objects.isNull(filmCategory)){
                response.setDeclinedTrasaction(null);
                return response;
            }
            filmCategoryRepository.deleteById(id);
            response.setSuccesTrasaction(filmCategory);
        }catch (Exception e){
            response.setFailService();
            System.out.println("Error: " + e.getMessage());
        }
        return response;
    }

}
