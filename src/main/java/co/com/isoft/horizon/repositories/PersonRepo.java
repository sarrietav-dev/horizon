package co.com.isoft.horizon.repositories;

import co.com.isoft.horizon.models.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepo extends CrudRepository<Person, Long> {
}
