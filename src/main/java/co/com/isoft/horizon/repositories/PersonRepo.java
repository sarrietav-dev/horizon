package co.com.isoft.horizon.repositories;

import co.com.isoft.horizon.models.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonRepo extends CrudRepository<Person, Long> {
  Optional<Person> findByName(String name);
}
