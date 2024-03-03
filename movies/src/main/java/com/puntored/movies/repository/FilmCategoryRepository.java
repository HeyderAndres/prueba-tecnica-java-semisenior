package com.puntored.movies.repository;

import com.puntored.movies.entity.Category;
import com.puntored.movies.entity.Film;
import com.puntored.movies.entity.FilmCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 *
 * @author heiderarellano
 */
public interface FilmCategoryRepository extends JpaRepository<FilmCategory, Long> {

    public Optional<FilmCategory> findByFilmAndCategory(Film film, Category category);
}
