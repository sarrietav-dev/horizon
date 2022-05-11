package co.com.isoft.horizon.services;

import co.com.isoft.horizon.models.Role;
import co.com.isoft.horizon.models.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role) throws DuplicateResourceException;
    User setRoleToUser(String roleName, Long id) throws ResourceNotFoundException;
    User getUser(Long id) throws ResourceNotFoundException;
    List<User> getUsers();

    Role getRole(String name) throws ResourceNotFoundException;
}
