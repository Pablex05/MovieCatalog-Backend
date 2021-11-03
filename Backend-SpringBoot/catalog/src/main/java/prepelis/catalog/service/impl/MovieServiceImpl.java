package prepelis.catalog.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prepelis.catalog.dto.MovieDto;
import prepelis.catalog.model.Actor;
import prepelis.catalog.model.Director;
import prepelis.catalog.model.Movie;
import prepelis.catalog.repository.ActorRepository;
import prepelis.catalog.repository.DirectorRepository;
import prepelis.catalog.repository.MovieRepository;
import prepelis.catalog.service.api.MovieService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Service annotation is used with classes that provide some business functionalities.
 * Spring context will autodetect these classes when annotation-based configuration and classpath scanning is used.
 */
@Service
public class MovieServiceImpl implements MovieService {

    @Resource
    private MovieRepository movieRepository;

    @Resource
    private ActorRepository actorRepository;

    @Resource
    private DirectorRepository directorRepository;

    @Transactional
    @Override
    public String addMovie(MovieDto movieDto) {
        if (movieRepository.findByTitle(movieDto.getTitle()) == null){
            Movie movie = new Movie();
            mapDtoToEntity(movieDto, movie);
            movieRepository.save(movie);
            return "Movie successfully created!";
        } else {
            return "Movie title already register";
        }
    }

    @Override
    public MovieDto getMovieById(Long movieId){
        Movie movie = movieRepository.findMovieById(movieId);
        return mapEntityToDto(movie);
    }

    @Override
    public MovieDto getMovieByTitle(String title) {
        Movie movie = movieRepository.findByTitle(title);
        return mapEntityToDto(movie);
    }

    @Override
    public List<MovieDto> getAllMovies() {
        List<MovieDto> movieDtos = new ArrayList<>();
        List<Movie> movies = movieRepository.findAll();
        movies.forEach(movie -> {
            MovieDto movieDto = mapEntityToDto(movie);
            movieDtos.add(movieDto);
        });
        return movieDtos;
    }

    @Transactional
    @Override
    public String updateMovie(Long movieId, MovieDto movieDto) {
        if (movieRepository.findByTitle(movieDto.getTitle()) == null){
            Movie std = movieRepository.findMovieById(movieId);
            std.removeActors();
            std.removeDirector(std.getDirector());
            mapDtoToEntity(movieDto, std);
            movieRepository.save(std);
            return "Movie successfully edited!";
        } else {
            return "Movie title already register";
        }
    }

    @Override
    public String deleteMovie(Long movieId) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        if(movie.isPresent()) {
            movieRepository.deleteById(movie.get().getId());
            return "Movie with id: " + movieId + " deleted successfully!";
        } else return "Movie not found in database";
    }

    private String mapDtoToEntity(MovieDto movieDto, Movie movie) {
        movie.setTitle(movieDto.getTitle());
        movie.setGenre(movieDto.getGenre());
        movie.setReleaseDate(movieDto.getReleaseDate());
        movie.setDuration(movieDto.getDuration());
        movie.setTrailer(movieDto.getTrailer());
        movie.setLanguage(movieDto.getLanguage());
        movie.setSubtitle(movieDto.getLanguage());
        //movie.removeActors();
        movie.setActors(new HashSet<>());
        for (String actorName : movieDto.getActors()) {
            Actor actor = actorRepository.findByName(actorName);
            if (null == actor) {
                actor = new Actor();
                actor.setMovies(new HashSet<>());
                actor.setName(actorName);
            }
            movie.addActor(actor);
        }

        String directorName = movieDto.getDirector();
        Director director = directorRepository.findByName(directorName);
        if (null == director) {
            director = new Director();
            director.setMovies(new HashSet<>());
            director.setName(directorName);
        }
        movie.addDirector(director);

        return null;
    }

    private MovieDto mapEntityToDto(Movie movie) {
        MovieDto responseDto = new MovieDto();
        responseDto.setId(movie.getId());
        responseDto.setTitle(movie.getTitle());
        responseDto.setGenre(movie.getGenre());
        responseDto.setReleaseDate(movie.getReleaseDate());
        responseDto.setDuration(movie.getDuration());
        responseDto.setTrailer(movie.getTrailer());
        responseDto.setLanguage(movie.getLanguage());
        responseDto.setSubtitle(movie.getSubtitle());
        responseDto.setActors(movie.getActors().stream().map(Actor::getName).collect(Collectors.toSet()));
        responseDto.setDirector(movie.getDirector().getName());
        return responseDto;
    }
}