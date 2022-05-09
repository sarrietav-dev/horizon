package co.com.isoft.horizon.controllers;

import co.com.isoft.horizon.DTO.PqrsDTO;
import co.com.isoft.horizon.models.PQRS;
import co.com.isoft.horizon.services.PqrsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/api/pqrs")
public class PQRSController {

    private final PqrsService pqrsService;

    public PQRSController(PqrsService pqrsService) {
        this.pqrsService = pqrsService;
    }

    public ResponseEntity<PqrsDTO> createPQRS(@RequestBody PqrsDTO dto) {
        PqrsDTO responseDTO = new PqrsDTO(pqrsService.create(new PQRS(dto)));

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
