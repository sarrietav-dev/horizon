package co.com.isoft.horizon.services.implementations;

import co.com.isoft.horizon.models.Person;
import co.com.isoft.horizon.repositories.PersonRepo;
import co.com.isoft.horizon.services.PersonService;
import co.com.isoft.horizon.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PersonServiceImplementation implements PersonService {
    private final PersonRepo personRepo;

    @Override
    public Person getPerson(String name) throws ResourceNotFoundException {
        log.info("Fetching the user with name {}", name);
        return personRepo.findByName(name).orElseThrow(() -> {
            log.error("The person with the name {} doesn't exist", name);
            return new ResourceNotFoundException(String.format("The person with the name %s doesn't exist", name));
        });
    }

    @Override
    public Person getPerson(Long id) throws ResourceNotFoundException {
        log.info("Fetching the user with id {}", id);
        return personRepo.findById(id).orElseThrow(() -> {
            log.error("The person with the id {} doesn't exist", id);
            return new ResourceNotFoundException(String.format("The person with the id %d doesn't exist", id));
        });
    }
}
