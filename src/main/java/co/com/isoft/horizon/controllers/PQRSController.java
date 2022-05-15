package co.com.isoft.horizon.controllers;

import co.com.isoft.horizon.DTO.PqrsDTO;
import co.com.isoft.horizon.models.PQRS;
import co.com.isoft.horizon.models.User;
import co.com.isoft.horizon.services.UserService;
import co.com.isoft.horizon.services.exceptions.ResourceNotFoundException;
import co.com.isoft.horizon.services.implementations.PqrsServiceImplementation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pqrs")
@Slf4j
public class PQRSController {

    private final PqrsServiceImplementation pqrsService;
    private final UserService userService;

    public PQRSController(PqrsServiceImplementation pqrsServiceImplementation, UserService userService) {
        this.pqrsService = pqrsServiceImplementation;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<PqrsDTO>> getAll() {
        return ResponseEntity.ok(pqrsService.getAll().stream().map(PqrsDTO::new).collect(Collectors.toList()));
    }

    @PostMapping("/")
    public ResponseEntity<?> createPQRS(@RequestBody PqrsDTO dto, Principal principal) {
        try {
            String userEmail = principal.getName();
            User currentAuthenticatedUser = userService.getUser(userEmail);
            PQRS pqrs = new PQRS(dto);
            pqrs.setPerson(currentAuthenticatedUser.getUserData());
            PqrsDTO responseDTO = new PqrsDTO(pqrsService.save(pqrs));

            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
