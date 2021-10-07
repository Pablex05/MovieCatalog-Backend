package prepelis.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import prepelis.app.dto.ActorDto;
import prepelis.app.service.api.ActorService;

@RestController
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping("/actor/{id}")
    public ResponseEntity<ActorDto> getActor(@PathVariable(name = "id") Long id) {
        ActorDto actor = actorService.getActor(id);
        return new ResponseEntity<>(actor, HttpStatus.OK);
    }

    @GetMapping("/actors")
    public ResponseEntity<List<ActorDto>> getAllActors() {
        List<ActorDto> actors = actorService.getAllActors();
        return new ResponseEntity<>(actors, HttpStatus.OK);
    }

    @PostMapping("/actor")
    public ResponseEntity<ActorDto> addActor(@RequestBody ActorDto actorDto) {
        ActorDto std = actorService.addActor(actorDto);
        return new ResponseEntity<>(std, HttpStatus.CREATED);
    }

    @PutMapping("/actor/{id}")
    public ResponseEntity<ActorDto> updateActor(@PathVariable(name = "id") Long id,
                                                  @RequestBody ActorDto actor) {
        ActorDto crs = actorService.updateActor(id, actor);
        return new ResponseEntity<>(crs, HttpStatus.CREATED);
    }

    @DeleteMapping("/actor/{id}")
    public ResponseEntity<String> deleteActor(@PathVariable(name = "id") Long id) {
        String message = actorService.deleteActor(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
