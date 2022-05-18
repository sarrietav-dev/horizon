package co.com.isoft.horizon.repositories;

import co.com.isoft.horizon.models.Apartment;
import org.springframework.data.repository.CrudRepository;

public interface ApartmentRepo extends CrudRepository<Apartment, Long> {}
