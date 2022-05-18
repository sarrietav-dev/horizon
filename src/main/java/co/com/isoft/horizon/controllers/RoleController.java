package co.com.isoft.horizon.controllers;

import co.com.isoft.horizon.models.Role;
import co.com.isoft.horizon.services.UserService;
import co.com.isoft.horizon.services.exceptions.DuplicateResourceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/role")
@Slf4j
public class RoleController {
  final UserService userService;

  public RoleController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  ResponseEntity<Role> createRole(@RequestBody Role role) {
    try {
      Role newRole = userService.saveRole(role);
      URI uri =
          URI.create(
              ServletUriComponentsBuilder.fromCurrentContextPath()
                  .path("/api/role/")
                  .toUriString());
      return ResponseEntity.created(uri).body(newRole);
    } catch (DuplicateResourceException e) {
      log.error(e.getMessage());
      throw new RuntimeException(e);
    }
  }
}
