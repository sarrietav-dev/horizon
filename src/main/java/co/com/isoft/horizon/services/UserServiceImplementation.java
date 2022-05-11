package co.com.isoft.horizon.services;

import co.com.isoft.horizon.models.Role;
import co.com.isoft.horizon.models.User;
import co.com.isoft.horizon.repositories.RoleRepo;
import co.com.isoft.horizon.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserServiceImplementation implements UserService, UserDetailsService {
    final UserRepo userRepo;
    final RoleRepo roleRepo;
    final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found in the database");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to the database", user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public User setRoleToUser(String roleName, Long id) throws ResourceNotFoundException {
        log.info("Setting role {} to the user with id {}", roleName, id);

        User user = this.getUser(id);
        Role role = this.getRole(roleName);

        user.setRole(role);

        return user;
    }

    @Override
    public User getUser(Long id) throws ResourceNotFoundException {
        return userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("The user with the id %d doesn't exist", id)));
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public Role getRole(String name) throws ResourceNotFoundException {
        return roleRepo.findByName(name).orElseThrow(() -> new ResourceNotFoundException(String.format("The role with the name %s doesn't exist", name)));
    }
}
