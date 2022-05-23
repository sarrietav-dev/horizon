package co.com.isoft.horizon.controllers;

import co.com.isoft.horizon.DTO.PqrsDTO;
import co.com.isoft.horizon.models.PQRS;
import co.com.isoft.horizon.models.Status;
import co.com.isoft.horizon.models.User;
import co.com.isoft.horizon.models.exceptions.ForbiddenStatusChangeException;
import co.com.isoft.horizon.services.UserService;
import co.com.isoft.horizon.services.exceptions.ResourceNotFoundException;
import co.com.isoft.horizon.services.implementations.PqrsServiceImplementation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/pqrs")
@Slf4j
public class PQRSController {

  private final PqrsServiceImplementation pqrsService;
  private final UserService userService;

  public PQRSController(
      PqrsServiceImplementation pqrsServiceImplementation, UserService userService) {
    this.pqrsService = pqrsServiceImplementation;
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<Page<PqrsDTO>> getAll(
      @RequestParam Optional<Integer> page, @RequestParam Optional<String> title) {
    return title
        .map(
            s ->
                ResponseEntity.ok(
                    pqrsService
                        .getAllThatMatchesTitle(PageRequest.of(page.orElse(0), 5), s)
                        .map(PqrsDTO::new)))
        .orElseGet(
            () ->
                ResponseEntity.ok(
                    pqrsService.getAll(PageRequest.of(page.orElse(0), 5)).map(PqrsDTO::new)));
  }

  @PostMapping
  public ResponseEntity<?> createPQRS(@RequestBody PqrsDTO dto, Principal principal) {
    try {
      String userEmail = principal.getName();
      User currentAuthenticatedUser = userService.getUser(userEmail);
      PQRS pqrs = new PQRS(dto);

      log.info(
          "Setting the person {} to the PQRS {}",
          currentAuthenticatedUser.getUserData().getName(),
          dto.getTitle());
      pqrs.setPerson(currentAuthenticatedUser.getUserData());

      pqrs.setCreationDate(new Date());
      PqrsDTO responseDTO = new PqrsDTO(pqrsService.save(pqrs));

      return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    } catch (ResourceNotFoundException e) {
      log.error(e.getMessage());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

  @PatchMapping("/{id}/status")
  public ResponseEntity<?> setStatus(@PathVariable String id, @RequestBody SetStatusForm status) {
    try {
      PQRS pqrs = pqrsService.get(Long.valueOf(id));
      return ResponseEntity.ok(new PqrsDTO(pqrsService.changeStatus(pqrs, status.getStatus())));
    } catch (ResourceNotFoundException e) {
      log.error(e.getMessage());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (ForbiddenStatusChangeException e) {
      log.error(e.getMessage());
      return ResponseEntity.badRequest().body(e.getMessage());
    } catch (NullPointerException e) {
      log.error(e.getMessage());
      return ResponseEntity.badRequest().body("Please check if any of the keys are correct.");
    }
  }

  @Data
  static class SetStatusForm {
    private Status status;
  }
}
