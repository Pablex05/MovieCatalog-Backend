package prepelis.catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import prepelis.catalog.dto.MovieDto;
import prepelis.catalog.exception.DataNotFoundException;
import prepelis.catalog.service.api.MovieService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Validated
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    /**
     * ResponseEntity represents an HTTP response, including headers, body, and status.
     * While @ResponseBody puts the return value into the body of the response,
     * ResponseEntity also allows us to add headers and status code.
     */

    @GetMapping("/getById/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable(name = "id") Long id) {
        try{
            MovieDto movie = movieService.getMovieById(id);
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } catch (NullPointerException e){
            throw new DataNotFoundException("Movie not found in database");
        }
    }

    @GetMapping("/getByTitle/{title}")
    public ResponseEntity<MovieDto> getMovieByTitle(@PathVariable(name = "title") String title){
        try {
            MovieDto movie = movieService.getMovieByTitle(title);
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } catch (NullPointerException e){
            throw new DataNotFoundException("Movie not found in database");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<MovieDto> movies = movieService.getAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addMovie(@Valid @RequestBody MovieDto movieDto) {
        String message = movieService.addMovie(movieDto);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<String> updateMovie(@PathVariable(name = "id") Long id,
                                              @Valid @RequestBody MovieDto movie) {
        try {
            String message = movieService.updateMovie(id, movie);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } catch (NullPointerException e){
            throw new DataNotFoundException("Movie not found in database");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable(name = "id") Long id) {
        String message = movieService.deleteMovie(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}

