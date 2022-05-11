package co.com.isoft.horizon.repositories;

import co.com.isoft.horizon.models.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepo extends CrudRepository<Role, Long>{
    Optional<Role> findByName(String name);
    boolean existsByName(String name);
}
