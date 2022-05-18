package co.com.isoft.horizon.services;

import co.com.isoft.horizon.models.Person;
import co.com.isoft.horizon.services.exceptions.ResourceNotFoundException;

public interface PersonService {
  Person getPerson(String name) throws ResourceNotFoundException;

  Person getPerson(Long id) throws ResourceNotFoundException;
}
