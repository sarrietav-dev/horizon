package co.com.isoft.horizon.DTO;

import co.com.isoft.horizon.models.Person;
import co.com.isoft.horizon.models.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;

    private String email;
    private String password;

    private Person userData;

    private Role role;

    public UserDTO(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
