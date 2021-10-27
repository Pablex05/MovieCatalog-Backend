package prepelis.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prepelis.catalog.model.Director;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
    Director findByName (String directorName);
    Director findDirectorById(Long directorId);
}
