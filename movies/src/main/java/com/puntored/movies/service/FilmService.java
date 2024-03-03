package com.puntored.movies.service;

import com.puntored.movies.dto.ApiResponseDTO;
import com.puntored.movies.dto.FilmRequestDTO;
import com.puntored.movies.dto.MoviesStoreDTO;
import com.puntored.movies.entity.Category;
import com.puntored.movies.entity.Film;
import com.puntored.movies.entity.FilmCategory;
import com.puntored.movies.repository.CategoryRepository;
import com.puntored.movies.repository.FilmCategoryRepository;
import com.puntored.movies.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FilmCategoryRepository filmCategoryRepository;

    public ApiResponseDTO<List<Film>> findAll() {
        ApiResponseDTO<List<Film>> response = new ApiResponseDTO<>();
        try {
            List<Film> dataResponse = filmRepository.findAll();
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

    public ApiResponseDTO<Film> findById(long id) {
        ApiResponseDTO<Film> response = new ApiResponseDTO<>();
        try {
            Optional<Film> dataResponse = filmRepository.findById(id);
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

    public ApiResponseDTO<Film> update(long id, FilmRequestDTO request) {
        ApiResponseDTO<Film> response = new ApiResponseDTO<>();
        try {
            Film dataResponse =  filmRepository.findById(id).orElse(null);
            if (Objects.isNull(dataResponse)) {
                response.setFailQuery("No se encontraron resultados");
                return response;
            }

            Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);
            if (Objects.isNull(category)){
                response.setDeclinedTrasaction(null, String.format("Valide que la categoria con id %s exista",
                        request.getCategoryId()));
                return response;
            }

            FilmCategory filmCategory = filmCategoryRepository.findByFilmAndCategory(dataResponse, category).orElse(null);
            if (Objects.isNull(filmCategory)){
                FilmCategory newFilmCategory = new FilmCategory();
                newFilmCategory.setFilm(dataResponse);
                newFilmCategory.setCategory(category);
                filmCategoryRepository.save(newFilmCategory);
            }

            setDataResponse(request, dataResponse);

            Film newFilm = filmRepository.save(dataResponse);
            response.setSuccesQuery(newFilm);

        } catch (Exception e) {
            response.setFailService();
            System.out.println("Error: " + e.getMessage());
        }
        return response;
    }

    public ApiResponseDTO<Film> create(FilmRequestDTO request) {
        ApiResponseDTO<Film> response = new ApiResponseDTO<>();
        try {
            Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);
            if (Objects.isNull(category)){
                response.setDeclinedTrasaction(null, String.format("Valide que la categoria con id %s exista",
                        request.getCategoryId()));
                return response;
            }

            Film dataResponse = new Film();
            setDataResponse(request, dataResponse);
            Film film = filmRepository.save(dataResponse);

            FilmCategory filmCategory = new FilmCategory();
            filmCategory.setCategory(category);
            filmCategory.setFilm(film);

            if (filmRepository.findById(film.getFilmId()).isEmpty()){
                response.setDeclinedTrasaction(film);
                return response;
            }

            response.setSuccesQuery(dataResponse);

        } catch (Exception e) {
            response.setFailService();
            System.out.println("Error: " + e.getMessage());
        }
        return response;
    }

    private void setDataResponse(FilmRequestDTO request, Film dataResponse) {
        dataResponse.setTitle(request.getTitle());
        dataResponse.setRating(request.getRating());
        dataResponse.setDuration(request.getDuration());
        dataResponse.setRentalDuration(request.getRentalDuration());
        dataResponse.setRentalPrice(request.getRentalPrice());
        dataResponse.setYearMovie(request.getYear());
        dataResponse.setDescription(request.getDescription());
    }

    public ApiResponseDTO<Film> delete(long id){
        ApiResponseDTO<Film> response = new ApiResponseDTO<>();
        try {
            Film film = filmRepository.findById(id).orElse(null);
            if (Objects.isNull(film)){
                response.setDeclinedTrasaction(null);
                return response;
            }
            filmRepository.deleteById(id);
            response.setSuccesTrasaction(film);
        }catch (Exception e){
            response.setFailService();
            System.out.println("Error: " + e.getMessage());
        }
        return response;
    }

    public ApiResponseDTO<List<MoviesStoreDTO>> findStoresAndQuantityByFilmId(Long filmId) {
        ApiResponseDTO<List<MoviesStoreDTO>> response = new ApiResponseDTO<>();
        try {
            List<Object[]> dataResponse = filmRepository.findMovieStoreAndQuantity(filmId);
            List<MoviesStoreDTO> results = new ArrayList<>();
            for (Object[] result : dataResponse){
                MoviesStoreDTO moviesStoreDTO = new MoviesStoreDTO();
                moviesStoreDTO.setAddress((String) result[0]);
                moviesStoreDTO.setQuantity((Integer) result[1]);
                results.add(moviesStoreDTO);
            }
            if (dataResponse.isEmpty()) {
                response.setFailQuery("No se encontraron resultados");
                return response;
            }
            response.setSuccesTrasaction(results);

        } catch (Exception e) {
            response.setFailService();
            System.out.println("Error: " + e.getMessage());
        }
        return response;
    }

}
