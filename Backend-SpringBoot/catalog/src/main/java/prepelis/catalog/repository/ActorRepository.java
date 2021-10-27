package prepelis.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prepelis.catalog.model.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long>{
    Actor findByName(String actorName);
    Actor findActorById(Long actorId);
}
