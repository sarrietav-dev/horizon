package co.com.isoft.horizon.services;

import co.com.isoft.horizon.models.Role;
import co.com.isoft.horizon.models.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    User setRoleToUser(String roleName, String userEmail);
    User getUser(String email);
    List<User> getUsers();
}
