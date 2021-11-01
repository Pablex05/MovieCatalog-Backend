package prepelis.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prepelis.catalog.model.Movie;

/**
 * JpaRepository contains the full API of CrudRepository and PagingAndSortingRepository.
 * So it contains API for basic CRUD operations and also API for pagination and sorting.
 */

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{
    Movie findByTitle(String movieTitle);
    Movie findMovieById(Long movieId);
}
