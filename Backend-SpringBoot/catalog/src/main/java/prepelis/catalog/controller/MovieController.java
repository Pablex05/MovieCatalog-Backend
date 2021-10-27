package prepelis.catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import prepelis.catalog.dto.MovieDto;
import prepelis.catalog.service.api.MovieService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable(name = "id") Long id) {
        MovieDto movie = movieService.getMovieById(id);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping("/getByTitle/{title}")
    public ResponseEntity<MovieDto> getMovieByTitle(@PathVariable(name = "title") String title){
        MovieDto movie = movieService.getMovieByTitle(title);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<MovieDto> movies = movieService.getAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> addMovie(@RequestBody MovieDto movieDto) {
        String message = movieService.addMovie(movieDto);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> updateMovie(@PathVariable(name = "id") Long id,
                                              @RequestBody MovieDto movie) {
        String message = movieService.updateMovie(id, movie);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteMovie(@PathVariable(name = "id") Long movieId) {
        String message = movieService.deleteMovie(movieId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}

