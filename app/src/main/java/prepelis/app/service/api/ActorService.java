package prepelis.app.service.api;

import java.util.List;
import prepelis.app.dto.ActorDto;

public interface ActorService {

    public ActorDto addActor(ActorDto actorDto);

    public ActorDto getActor(Long id);

    public List<ActorDto> getAllActors();

    public ActorDto updateActor(Long id, ActorDto actor);

    public String deleteActor(Long id);

}
