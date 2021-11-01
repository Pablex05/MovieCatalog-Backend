package prepelis.catalog.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import prepelis.catalog.user.models.RefreshToken;
import prepelis.catalog.user.models.User;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

  Optional<RefreshToken> findByToken(String token);

  /**
   * Using @Modifying will remove any pending updates to managed authorizations in the persistence context.
   */
  @Modifying
  int deleteByUser(User user);
}
