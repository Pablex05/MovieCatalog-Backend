package prepelis.catalog.user.security.services;

import org.springframework.stereotype.Service;
import prepelis.catalog.user.models.User;
import prepelis.catalog.user.repository.UserRepository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserRepository userRepository;

    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepository.findUserById(userId);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
