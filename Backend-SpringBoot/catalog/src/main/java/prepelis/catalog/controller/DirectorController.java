package prepelis.catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import prepelis.catalog.dto.DirectorDto;
import prepelis.catalog.exception.DataNotFoundException;
import prepelis.catalog.service.api.DirectorService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Validated
@RequestMapping("director")
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<DirectorDto> getDirectorById(@PathVariable(name = "id") Long id){
        try {
            DirectorDto director = directorService.getDirectorById(id);
            return new ResponseEntity<>(director, HttpStatus.OK);
        } catch (NullPointerException e){
            throw new DataNotFoundException("Director not found in database");
        }
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<DirectorDto> getActorById(@PathVariable(name = "name") String name){
        try {
            DirectorDto director = directorService.getDirectorByName(name);
            return new ResponseEntity<>(director, HttpStatus.OK);
        } catch (NullPointerException e){
            throw new DataNotFoundException("Director not found in database");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DirectorDto>> getAllDirectors() {
        List<DirectorDto> directors = directorService.getAllDirectors();
        return new ResponseEntity<>(directors, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addDirector(@Valid @RequestBody DirectorDto directorDto) {
        String message = directorService.addDirector(directorDto);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<String> updateDirector(@PathVariable(name = "id") Long id,
                                                 @Valid @RequestBody DirectorDto directorDto) {
        try {
            String message = directorService.updateDirector(id, directorDto);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } catch (NullPointerException e){
            throw new DataNotFoundException("Director not found in database");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDirector(@PathVariable(name = "id") Long id) {
        String message = directorService.deleteDirector(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
