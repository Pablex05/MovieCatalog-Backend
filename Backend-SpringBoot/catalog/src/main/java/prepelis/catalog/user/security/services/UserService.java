package prepelis.catalog.user.security.services;


import prepelis.catalog.user.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public Optional<User> getUserById(Long userId);

    public Optional<User> getUserByUsername(String name);

    public Optional<User> getUserByEmail(String email);

    public List<User> getAllUsers();
}
