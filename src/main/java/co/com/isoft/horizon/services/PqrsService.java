package co.com.isoft.horizon.services;

import co.com.isoft.horizon.models.PQRS;

import java.util.List;

public interface PqrsService {
    List<PQRS> getAll();

    PQRS save(PQRS pqrs);
}
