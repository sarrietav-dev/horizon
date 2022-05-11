package co.com.isoft.horizon.repositories;

import co.com.isoft.horizon.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends CrudRepository<Role, Long>{
    Role findByName(String name);
}
