package co.com.isoft.horizon.controllers;

import co.com.isoft.horizon.DTO.UserDTO;
import co.com.isoft.horizon.models.User;
import co.com.isoft.horizon.services.UserService;
import co.com.isoft.horizon.services.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {
  final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
    try {
      User user = User.from(userDTO);

      user.setRole(userService.getRole(userDTO.getRole()));

      URI uri =
          URI.create(
              ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user").toUriString());

      return ResponseEntity.created(uri).body(userService.saveUser(user));
    } catch (ResourceNotFoundException e) {
      log.error(e.getMessage());
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PatchMapping("/{id}/role")
  public ResponseEntity<User> updateRole(@RequestBody UpdateRoleForm body, @PathVariable Long id) {
    try {
      User newUser = userService.setRoleToUser(body.getRoleName(), id);
      return ResponseEntity.accepted().body(newUser);
    } catch (ResourceNotFoundException e) {
      log.error(e.getMessage());
      return ResponseEntity.notFound().build();
    }
  }

  @Data
  @AllArgsConstructor
  static class UpdateRoleForm {
    private String roleName;
  }
}
