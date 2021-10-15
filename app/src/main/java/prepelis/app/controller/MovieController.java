package prepelis.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import prepelis.app.dto.DirectorDto;
import prepelis.app.dto.MovieDto;
import prepelis.app.service.api.MovieService;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<MovieDto> getMovie(@PathVariable(name = "id") Long id) {
        MovieDto movie = movieService.getMovie(id);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<MovieDto> movies = movieService.getAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto movieDto) {
        MovieDto std = movieService.addMovie(movieDto);
        return new ResponseEntity<>(std, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<MovieDto> updateMovie(@PathVariable(name = "id") Long id,
                                                @RequestBody MovieDto movie) {
        MovieDto std = movieService.updateMovie(id, movie);
        return new ResponseEntity<>(std, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteMovie(@PathVariable(name = "id") Long movieId) {
        String message = movieService.deleteMovie(movieId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
