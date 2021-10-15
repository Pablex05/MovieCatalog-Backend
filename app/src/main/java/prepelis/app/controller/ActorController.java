package prepelis.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import prepelis.app.dto.ActorDto;
import prepelis.app.service.api.ActorService;

@RestController
@RequestMapping("/actor")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<ActorDto> getActor(@PathVariable(name = "id") Long id) {
        ActorDto actor = actorService.getActor(id);
        return new ResponseEntity<>(actor, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ActorDto>> getAllActors() {
        List<ActorDto> actors = actorService.getAllActors();
        return new ResponseEntity<>(actors, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ActorDto> addActor(@RequestBody ActorDto actorDto) {
        ActorDto std = actorService.addActor(actorDto);
        return new ResponseEntity<>(std, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ActorDto> updateActor(@PathVariable(name = "id") Long id,
                                                  @RequestBody ActorDto actor) {
        ActorDto crs = actorService.updateActor(id, actor);
        return new ResponseEntity<>(crs, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteActor(@PathVariable(name = "id") Long id) {
        String message = actorService.deleteActor(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
