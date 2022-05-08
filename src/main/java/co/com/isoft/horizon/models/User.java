package co.com.isoft.horizon.models;

import co.com.isoft.horizon.DTO.UserDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "auth_user")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "authData", fetch = FetchType.LAZY)
    private Person userData;

    private Role role;

    public User(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    protected User() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    /**
     * Converts from DTO to Entity
     */
    public static User from(UserDTO dto) {
        return new User(dto.getEmail(), dto.getPassword(), dto.getRole());
    }
}
