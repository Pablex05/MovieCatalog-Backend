package prepelis.catalog.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import prepelis.catalog.exception.DataNotFoundException;
import prepelis.catalog.user.models.User;
import prepelis.catalog.user.security.services.UserService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.getUserById(id)
                .orElseThrow(() -> new DataNotFoundException("User Not Found with id: " + id));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/getByUsername/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable(name = "username") String username) {
        User user = userService.getUserByUsername(username)
                .orElseThrow(() -> new DataNotFoundException("User Not Found with username: " + username));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable(name = "email") String email) {

        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new DataNotFoundException("User Not Found with email: " + email));
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
