package prepelis.app.service.impl;

import prepelis.app.dto.MovieDto;
import prepelis.app.model.Actor;
import prepelis.app.model.Director;
import prepelis.app.model.Movie;
import prepelis.app.repository.ActorRepository;
import prepelis.app.repository.DirectorRepository;
import prepelis.app.repository.MovieRepository;
import prepelis.app.service.api.MovieService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prepelis.app.user.models.User;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public MovieDto addMovie(MovieDto movieDto) {
        Movie movie = new Movie();
        mapDtoToEntity(movieDto, movie);
        Movie savedMovie = movieRepository.save(movie);
        return mapEntityToDto(savedMovie);
    }

    @Override
    public MovieDto getMovie(Long id) {
        MovieDto movieDto = new MovieDto();
        Movie movie = movieRepository.findMovieById(id);
        try {
            movieDto = mapEntityToDto(movie);
            return movieDto;
        }catch (Exception e){
            e.getCause();
        }
        return null;
    }

    @Override
    public List<MovieDto> getAllMovies() {
        List<MovieDto> moviesDto = new ArrayList<>();
        List<Movie> movies = movieRepository.findAll();
        movies.forEach(movie -> {
            MovieDto movieDto = mapEntityToDto(movie);
            moviesDto.add(movieDto);
        });
        return moviesDto;
    }

    @Transactional
    @Override
    public MovieDto updateMovie(Long id, MovieDto movieDto) {
        Movie std = movieRepository.getById(id);
        Actor actor = actorRepository.getById(id);
        actor.removeMovie(std);
        mapDtoToEntity(movieDto, std);
        Movie movie = movieRepository.save(std);
        return mapEntityToDto(movie);
    }

    @Override
    public String deleteMovie(Long movieId) {

        Optional<Movie> movie = movieRepository.findById(movieId);
        if(movie.isPresent()) {
            movieRepository.deleteById(movie.get().getId());
            return "Movie with id: " + movieId + " deleted successfully!";
        }
        return null;
    }

    private void mapDtoToEntity(MovieDto movieDto, Movie movie) {
        movie.setTitle(movieDto.getTitle());
        movie.setTrailer(movieDto.getTrailer());
        movie.setLanguage(movieDto.getLanguage());
        movie.setSubtitle(movieDto.getSubtitle());
        movie.setGenre(movieDto.getGenre());
        movie.setDuration(movieDto.getDuration());
        movie.setReleaseDate(movieDto.getReleaseDate());

        if (null == movie.getActors()) {
            movie.setActors(new HashSet<>());
        }
        for (String actorName : movieDto.getActors()) {
            Actor actor = actorRepository.findByName(actorName);
            if (null == actor) {
                actor = new Actor();
                actor.setMovies(new HashSet<>());
            }
            actor.setName(actorName);
            movie.addActor(actor);
        }

        if (null == movie.getDirector()) {
            movie.setDirector(new HashSet<>());
        }
        for (String directorName : movieDto.getDirector()) {
            Director director = directorRepository.findByName(directorName);
            if (null == director) {
                director = new Director();
                director.setMovies(new HashSet<>());
            }
            director.setName(directorName);
            movie.addDirector(director);
        }
    }

    private MovieDto mapEntityToDto(Movie movie) {
        MovieDto responseDto = new MovieDto();
        responseDto.setId(movie.getId());
        responseDto.setTitle(movie.getTitle());
        responseDto.setTrailer(movie.getTrailer());
        responseDto.setLanguage(movie.getLanguage());
        responseDto.setSubtitle(movie.getSubtitle());
        responseDto.setGenre(movie.getGenre());
        responseDto.setDuration(movie.getDuration());
        responseDto.setReleaseDate(movie.getReleaseDate());
        responseDto.setActors(
                movie.getActors().
                stream().
                map(Actor::getName).
                collect(Collectors.toSet()));
        responseDto.setDirector(
                movie.getDirector().
                        stream().
                        map(Director::getName).
                        collect(Collectors.toSet()));
        return responseDto;
    }
}
