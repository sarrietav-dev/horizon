package co.com.isoft.horizon.services;

import co.com.isoft.horizon.models.PQRS;
import co.com.isoft.horizon.models.Status;
import co.com.isoft.horizon.models.exceptions.ForbiddenStatusChangeException;
import co.com.isoft.horizon.services.exceptions.ResourceNotFoundException;

import java.util.List;

public interface PqrsService {
    List<PQRS> getAll();

    PQRS save(PQRS pqrs);

    PQRS changeStatus(PQRS pqrs, Status status) throws ResourceNotFoundException, ForbiddenStatusChangeException;
}
