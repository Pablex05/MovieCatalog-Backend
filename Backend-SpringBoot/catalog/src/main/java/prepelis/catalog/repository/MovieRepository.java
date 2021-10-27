package prepelis.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prepelis.catalog.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{
    Movie findByTitle(String movieTitle);
    Movie findMovieById(Long movieId);
}
