package prepelis.catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import prepelis.catalog.dto.ActorDto;
import prepelis.catalog.service.api.ActorService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("actor")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping("/getById/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ActorDto> getActorById(@PathVariable(name = "id") Long id){
        ActorDto actor = actorService.getActorById(id);
        return new ResponseEntity<>(actor, HttpStatus.OK);
    }

    @GetMapping("/getByName/{name}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ActorDto> getActorById(@PathVariable(name = "name") String name){
        ActorDto actor = actorService.getActorByName(name);
        return new ResponseEntity<>(actor, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<ActorDto>> getAllActors() {
        List<ActorDto> actors = actorService.getAllActors();
        return new ResponseEntity<>(actors, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addActor(@RequestBody ActorDto actorDto) {
        String message = actorService.addActor(actorDto);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateActor(@PathVariable(name = "id") Long id,
                                              @RequestBody ActorDto actorDto) {
        String message = actorService.updateActor(id, actorDto);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteActor(@PathVariable(name = "id") Long id) {
        String message = actorService.deleteActor(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}

