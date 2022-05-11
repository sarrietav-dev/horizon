package co.com.isoft.horizon.controllers;

import co.com.isoft.horizon.models.Role;
import co.com.isoft.horizon.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {
    UserService userService;

    @PostMapping("/")
    ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role newRole = userService.saveRole(role);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/").toUriString());
        return ResponseEntity.created(uri).body(newRole);
    }
}
