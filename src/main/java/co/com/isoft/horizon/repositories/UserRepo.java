package co.com.isoft.horizon.repositories;

import co.com.isoft.horizon.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Long> {
  Optional<User> findByEmail(String email);
}
