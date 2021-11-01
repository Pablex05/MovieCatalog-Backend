package prepelis.catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import prepelis.catalog.dto.MovieDto;
import prepelis.catalog.exception.DataNotFoundException;
import prepelis.catalog.service.api.MovieService;
import prepelis.catalog.user.exception.TokenRefreshException;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/getById/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable(name = "id") Long id) {
        try{
            MovieDto movie = movieService.getMovieById(id);
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } catch (NullPointerException e){
            throw new DataNotFoundException("Movie not found in database");
        }

    }

    @GetMapping("/getByTitle/{title}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<MovieDto> getMovieByTitle(@PathVariable(name = "title") String title){
        try {
            MovieDto movie = movieService.getMovieByTitle(title);
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } catch (NullPointerException e){
            throw new DataNotFoundException("Movie not found in database");
        }

    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        try {
            List<MovieDto> movies = movieService.getAllMovies();
            return new ResponseEntity<>(movies, HttpStatus.OK);
        } catch (NullPointerException e){
            throw new DataNotFoundException("Movie not found in database");
        }
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addMovie(@RequestBody MovieDto movieDto) {
        String message = movieService.addMovie(movieDto);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateMovie(@PathVariable(name = "id") Long id,
                                              @RequestBody MovieDto movie) {
        try {
            String message = movieService.updateMovie(id, movie);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } catch (NullPointerException e){
            throw new DataNotFoundException("Movie not found in database");
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteMovie(@PathVariable(name = "id") Long movieId) {
        try {
            String message = movieService.deleteMovie(movieId);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (NullPointerException e){
            throw new DataNotFoundException("Movie not found in database");
        }
    }
}

