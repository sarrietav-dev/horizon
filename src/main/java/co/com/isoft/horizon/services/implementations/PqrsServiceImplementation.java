package co.com.isoft.horizon.services.implementations;

import co.com.isoft.horizon.models.PQRS;
import co.com.isoft.horizon.models.Status;
import co.com.isoft.horizon.models.exceptions.ForbiddenStatusChangeException;
import co.com.isoft.horizon.repositories.PQRSRepo;
import co.com.isoft.horizon.services.PqrsService;
import co.com.isoft.horizon.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PqrsServiceImplementation implements PqrsService {
    private final PQRSRepo pqrsRepo;

    @Override
    public List<PQRS> getAll() {
        log.info("Getting all the PQRS");
        return (List<PQRS>) pqrsRepo.findAll();
    }

    @Override
    public PQRS save(PQRS pqrs) {
        log.info("Saving the PQRS with title {}", pqrs.getTitle());
        return pqrsRepo.save(pqrs);
    }

    @Override
    public PQRS changeStatus(PQRS pqrs, Status status) throws ResourceNotFoundException, ForbiddenStatusChangeException {
        log.info("Changing the status of {} to {}", pqrs.getTitle(), status.name());
        PQRS foundPQRS = pqrsRepo.findById(pqrs.getId()).orElseThrow(() -> new ResourceNotFoundException("The pqrs wasn't found"));
        foundPQRS.setStatus(status);
        return pqrsRepo.save(foundPQRS);
    }

}
