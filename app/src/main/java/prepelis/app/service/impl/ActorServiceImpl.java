package prepelis.app.service.impl;

import prepelis.app.repository.ActorRepository;
import prepelis.app.repository.MovieRepository;
import prepelis.app.model.Actor;
import prepelis.app.model.Movie;
import prepelis.app.service.api.ActorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prepelis.app.dto.ActorDto;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActorServiceImpl implements ActorService {

    @Resource
    private MovieRepository movieRepository;

    @Resource
    private ActorRepository actorRepository;

    @Transactional
    @Override
    public ActorDto addActor(ActorDto actorDto) {
        Actor actor = new Actor();
        mapDtoToEntity(actorDto, actor);
        Actor savedActor = actorRepository.save(actor);
        return mapEntityToDto(savedActor);
    }

    @Override
    public List<ActorDto> getAllActors() {
        List<ActorDto> actorDtos = new ArrayList<>();
        List<Actor> actors = actorRepository.findAll();
        actors.forEach(actor -> {
            ActorDto actorDto = mapEntityToDto(actor);
            actorDtos.add(actorDto);
        });
        return actorDtos;
    }

    @Transactional
    @Override
    public ActorDto updateActor(Long id, ActorDto actorDto) {
        Actor crs = actorRepository.getOne(id);
        Movie std = movieRepository.getOne(id);
        std.removeActor(crs);
        mapDtoToEntity(actorDto, crs);
        Actor actor = actorRepository.save(crs);
        return mapEntityToDto(actor);
    }

    @Transactional
    @Override
    public String deleteActor(Long id) {
        Optional<Actor> actor = actorRepository.findById(id);
        if(actor.isPresent()) {
            actor.get().removeMovies();
            actorRepository.deleteById(actor.get().getId());
            return "Actor with id: " + id + " deleted successfully!";
        }
        return null;
    }

    private void mapDtoToEntity(ActorDto actorDto, Actor actor) {
        actor.setName(actorDto.getName());
        if (null == actor.getMovies()) {
            actor.setMovies(new HashSet<>());
        }
        actorDto.getMovies().forEach(movieTitle -> {
            Movie movie = movieRepository.findByTitle(movieTitle);
            if (null == movie) {
                movie = new Movie();
                movie.setActors(new HashSet<>());
            }
            movie.setTitle(movieTitle);
            movie.addActor(actor);
        });
    }

    private ActorDto mapEntityToDto(Actor actor) {
        ActorDto responseDto = new ActorDto();
        responseDto.setName(actor.getName());
        responseDto.setId(actor.getId());
        responseDto.setMovies(actor.getMovies().stream().map(Movie::getTitle).collect(Collectors.toSet()));
        return responseDto;
    }
}