package prepelis.catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import prepelis.catalog.dto.DirectorDto;
import prepelis.catalog.service.api.DirectorService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("director")
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<DirectorDto> getDirectorById(@PathVariable(name = "id") Long id){
        DirectorDto director = directorService.getDirectorById(id);
        return new ResponseEntity<>(director, HttpStatus.OK);
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<DirectorDto> getActorById(@PathVariable(name = "name") String name){
        DirectorDto director = directorService.getDirectorByName(name);
        return new ResponseEntity<>(director, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DirectorDto>> getAllDirectors() {
        List<DirectorDto> directors = directorService.getAllDirectors();
        return new ResponseEntity<>(directors, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> addDirector(@RequestBody DirectorDto directorDto) {
        String message = directorService.addDirector(directorDto);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> updateDirector(@PathVariable(name = "id") Long id,
                                                 @RequestBody DirectorDto directorDto) {
        String message = directorService.updateDirector(id, directorDto);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteDirector(@PathVariable(name = "id") Long id) {
        String message = directorService.deleteDirector(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
