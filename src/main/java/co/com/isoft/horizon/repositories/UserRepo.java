package co.com.isoft.horizon.repositories;

import co.com.isoft.horizon.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
