package co.com.isoft.horizon.controllers;

import co.com.isoft.horizon.DTO.UserDTO;
import co.com.isoft.horizon.models.Person;
import co.com.isoft.horizon.models.User;
import co.com.isoft.horizon.services.UserNotFoundException;
import co.com.isoft.horizon.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public User createUser(@RequestBody UserDTO userDTO) {
        User user = User.from(userDTO);

        Person person = userDTO.getUserData().toEntity();
        user.setUserData(person);
        person.setAuthData(user);

        return userService.saveUser(user);
    }

    @PatchMapping("/{id}/role")
    public ResponseEntity<User> updateRole(@RequestBody UpdateRoleForm body, @PathVariable Long id) {
        try {
            User newUser = userService.setRoleToUser(body.getRoleName(), id);
            return ResponseEntity.accepted().body(newUser);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Data
    @AllArgsConstructor
    static
    class UpdateRoleForm {
        private String roleName;
    }
}
