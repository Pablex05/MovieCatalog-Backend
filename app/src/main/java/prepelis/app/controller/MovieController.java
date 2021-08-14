package prepelis.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import prepelis.app.dto.MovieDto;
import prepelis.app.service.api.MovieService;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<MovieDto> movies = movieService.getAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @PostMapping("/movie")
    public ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto movieDto) {
        MovieDto std = movieService.addMovie(movieDto);
        return new ResponseEntity<>(std, HttpStatus.CREATED);
    }

    @PutMapping("/movie/{id}")
    public ResponseEntity<MovieDto> updateMovie(@PathVariable(name = "id") Long id,
                                                @RequestBody MovieDto movie) {
        MovieDto std = movieService.updateMovie(id, movie);
        return new ResponseEntity<>(std, HttpStatus.CREATED);
    }

    @DeleteMapping("/movie/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable(name = "id") Long movieId) {
        String message = movieService.deleteMovie(movieId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
