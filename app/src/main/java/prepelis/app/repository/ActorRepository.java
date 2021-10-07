package prepelis.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prepelis.app.model.Actor;

public interface ActorRepository extends JpaRepository<Actor, Long>{

    Actor findByName(String actorName);

    Actor findActorById(Long id);

}
