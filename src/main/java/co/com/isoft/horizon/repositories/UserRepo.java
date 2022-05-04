package co.com.isoft.horizon.repositories;

import co.com.isoft.horizon.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
}
