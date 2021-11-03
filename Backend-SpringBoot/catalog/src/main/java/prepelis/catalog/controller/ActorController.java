package prepelis.catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import prepelis.catalog.dto.ActorDto;
import prepelis.catalog.exception.DataNotFoundException;
import prepelis.catalog.service.api.ActorService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Validated
@RequestMapping("actor")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<ActorDto> getActorById(@PathVariable(name = "id") Long id){
        try {
            ActorDto actor = actorService.getActorById(id);
            return new ResponseEntity<>(actor, HttpStatus.OK);
        } catch (NullPointerException e){
            throw new DataNotFoundException("Actor not found in database");
        }
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<ActorDto> getActorById(@PathVariable(name = "name") String name){
        try {
            ActorDto actor = actorService.getActorByName(name);
            return new ResponseEntity<>(actor, HttpStatus.OK);
        } catch (NullPointerException e){
            throw new DataNotFoundException("Actor not found in database");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ActorDto>> getAllActors() {
        List<ActorDto> actors = actorService.getAllActors();
        return new ResponseEntity<>(actors, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addActor(@Valid  @RequestBody ActorDto actorDto) {
        String message = actorService.addActor(actorDto);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<String> updateActor(@Valid @PathVariable(name = "id") Long id,
                                              @RequestBody ActorDto actorDto) {
        try {
            String message = actorService.updateActor(id, actorDto);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } catch (NullPointerException e){
            throw new DataNotFoundException("Actor not found in database");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteActor(@PathVariable(name = "id") Long id) {
        String message = actorService.deleteActor(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}

