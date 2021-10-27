package prepelis.catalog.service.api;

import prepelis.catalog.dto.MovieDto;
import java.util.List;

public interface MovieService {

    public String addMovie(MovieDto movieDto);

    public MovieDto getMovieById(Long movieId);

    public MovieDto getMovieByTitle(String title);

    public List<MovieDto> getAllMovies();

    public String updateMovie(Long movieId, MovieDto movie);

    public String deleteMovie(Long movieId);
}
