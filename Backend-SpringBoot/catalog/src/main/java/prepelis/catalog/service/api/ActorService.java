package prepelis.catalog.service.api;

import prepelis.catalog.dto.ActorDto;
import java.util.List;

public interface ActorService {

    public String addActor(ActorDto actorDto);

    public ActorDto getActorById(Long actorId);

    public ActorDto getActorByName(String name);

    public List<ActorDto> getAllActors();

    public String updateActor(Long actorId, ActorDto actor);

    public String deleteActor(Long actorId);

}
