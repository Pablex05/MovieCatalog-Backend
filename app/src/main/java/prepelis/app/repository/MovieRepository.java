package prepelis.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prepelis.app.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{

    Movie findByTitle(String movieTitle);

    Movie findMovieById(Long id);

}
