package co.com.isoft.horizon.controllers;

import co.com.isoft.horizon.DTO.PqrsDTO;
import co.com.isoft.horizon.models.PQRS;
import co.com.isoft.horizon.models.User;
import co.com.isoft.horizon.services.UserService;
import co.com.isoft.horizon.services.exceptions.ResourceNotFoundException;
import co.com.isoft.horizon.services.implementations.PqrsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/pqrs")
@Slf4j
public class PQRSController {

    private final PqrsService pqrsService;
    private final UserService userService;

    public PQRSController(PqrsService pqrsService, UserService userService) {
        this.pqrsService = pqrsService;
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<?> createPQRS(@RequestBody PqrsDTO dto, Principal principal) {
        try {
            String userEmail = principal.getName();
            User currentAuthenticatedUser = userService.getUser(userEmail);
            PQRS pqrs = new PQRS(dto);
            pqrs.setPerson(currentAuthenticatedUser.getUserData());
            PqrsDTO responseDTO = new PqrsDTO(pqrsService.create(pqrs));

            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
