package prepelis.app.service.api;

import java.util.List;

import prepelis.app.dto.ActorDto;
import prepelis.app.dto.MovieDto;

public interface MovieService {

    public MovieDto addMovie(MovieDto movieDto);

    public MovieDto getMovie(Long id);

    public List<MovieDto> getAllMovies();

    public MovieDto updateMovie(Long movieId, MovieDto movie);

    public String deleteMovie(Long movieId);
}
