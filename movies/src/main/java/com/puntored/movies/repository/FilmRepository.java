package com.puntored.movies.repository;

import com.puntored.movies.dto.MoviesStoreDTO;
import com.puntored.movies.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 *
 * @author heiderarellano
 */
public interface FilmRepository extends JpaRepository<Film, Long> {

    @Query(value = "SELECT s.address, i.quantity " +
            "FROM inventory i " +
            "JOIN store s ON i.store_id = s.store_id " +
            "JOIN film f ON i.film_id = f.film_id " +
            "WHERE f.film_id = :film_id ", nativeQuery = true)
    public List<Object[]> findMovieStoreAndQuantity(@Param("film_id") Long filmId);
}
