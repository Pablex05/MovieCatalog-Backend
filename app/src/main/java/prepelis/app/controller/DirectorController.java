package prepelis.app.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import prepelis.app.dto.ActorDto;
import prepelis.app.dto.DirectorDto;
import prepelis.app.service.api.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/director")
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<DirectorDto> getDirector(@PathVariable(name = "id") Long id) {
        DirectorDto director = directorService.getDirector(id);
        return new ResponseEntity<>(director, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DirectorDto>> getAllDirectors() {
        List<DirectorDto> directors = directorService.getAllDirectors();
        return new ResponseEntity<>(directors, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<DirectorDto> addDirector(@RequestBody DirectorDto directorDto) {
        DirectorDto std = directorService.addDirector(directorDto);
        return new ResponseEntity<>(std, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<DirectorDto> updateDirector(@PathVariable(name = "id") Long id,
                                                @RequestBody DirectorDto director) {
        DirectorDto crs = directorService.updateDirector(id, director);
        return new ResponseEntity<>(crs, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteDirector(@PathVariable(name = "id") Long id) {
        String message = directorService.deleteDirector(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
