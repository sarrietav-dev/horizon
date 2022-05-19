package co.com.isoft.horizon.services;

import co.com.isoft.horizon.models.PQRS;
import co.com.isoft.horizon.models.Status;
import co.com.isoft.horizon.models.exceptions.ForbiddenStatusChangeException;
import co.com.isoft.horizon.services.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface PqrsService {

  Page<PQRS> getAll(PageRequest pageRequest);

  PQRS save(PQRS pqrs);

  PQRS changeStatus(PQRS pqrs, Status status)
      throws ResourceNotFoundException, ForbiddenStatusChangeException;

  PQRS get(Long id) throws ResourceNotFoundException;
}
