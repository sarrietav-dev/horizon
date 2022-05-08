package co.com.isoft.horizon.controllers;

import co.com.isoft.horizon.DTO.UserDTO;
import co.com.isoft.horizon.models.Person;
import co.com.isoft.horizon.models.Resident;
import co.com.isoft.horizon.models.Role;
import co.com.isoft.horizon.models.User;
import co.com.isoft.horizon.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/api/users")
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public User createUser(@RequestBody UserDTO userDTO) {
        User user = User.from(userDTO);
        switch (user.getRole()) {
            case ADMIN:
            case PROPRIETARY:
                break;
            case RESIDENT:
                Person person = Resident.from(userDTO.getUserData());
                user.setUserData(person);
                person.setAuthData(user);
                break;
        }

        return userService.createUser(user);
    }
}
