package repository;

import com.puntored.movies.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author heiderarellano
 */
public interface FilmRepository extends JpaRepository<Film, Long> {
    
}
