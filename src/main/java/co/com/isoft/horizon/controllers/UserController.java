package co.com.isoft.horizon.controllers;

import co.com.isoft.horizon.models.User;
import co.com.isoft.horizon.repositories.UserRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/api/users")
public class UserController {
    final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping("/")
    public User createUser(@RequestBody User user) {
        return userRepo.save(user);
    }
}
