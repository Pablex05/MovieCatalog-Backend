package prepelis.app.repository;

import prepelis.app.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Long>{

    Director findByName(String directorName);

}
