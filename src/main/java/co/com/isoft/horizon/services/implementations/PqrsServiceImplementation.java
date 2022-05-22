package co.com.isoft.horizon.services.implementations;

import co.com.isoft.horizon.models.PQRS;
import co.com.isoft.horizon.models.Status;
import co.com.isoft.horizon.models.exceptions.ForbiddenStatusChangeException;
import co.com.isoft.horizon.repositories.PQRSRepo;
import co.com.isoft.horizon.services.PqrsService;
import co.com.isoft.horizon.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PqrsServiceImplementation implements PqrsService {
  private final PQRSRepo pqrsRepo;

  @Override
  public Page<PQRS> getAll(PageRequest pageRequest) {
    log.info("Getting the page {} of PQRS", pageRequest.getPageNumber());
    return pqrsRepo.findAll(pageRequest);
  }

  @Override
  public Page<PQRS> getAllThatMatchesTitle(Pageable pageRequest, String title) {
    log.info("Getting all PQRS that contains the title: {}", title);
    return pqrsRepo.getAllByTitleContainingIgnoreCase(title, pageRequest);
  }

  @Override
  public PQRS save(PQRS pqrs) {
    log.info("Saving the PQRS with title {}", pqrs.getTitle());
    return pqrsRepo.save(pqrs);
  }

  @Override
  public PQRS changeStatus(PQRS pqrs, Status status)
      throws ResourceNotFoundException, ForbiddenStatusChangeException {
    log.info("Changing the status of {} to {}", pqrs.getTitle(), status.name());
    PQRS foundPQRS =
        pqrsRepo
            .findById(pqrs.getId())
            .orElseThrow(() -> new ResourceNotFoundException("The pqrs wasn't found"));
    foundPQRS.setStatus(status);
    return pqrsRepo.save(foundPQRS);
  }

  @Override
  public PQRS get(Long id) throws ResourceNotFoundException {
    log.info("Getting a PQRS with the id: {}", id);
    return pqrsRepo
        .findById(id)
        .orElseThrow(
            () ->
                new ResourceNotFoundException(
                    String.format("The PQRS with the id %d was not found", id)));
  }
}
