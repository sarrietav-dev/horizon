package co.com.isoft.horizon.services;

import co.com.isoft.horizon.models.Role;
import co.com.isoft.horizon.models.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    User setRoleToUser(String roleName, Long id) throws UserNotFoundException;
    User getUser(Long id) throws UserNotFoundException;
    List<User> getUsers();
}
