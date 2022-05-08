package co.com.isoft.horizon.DTO;

import co.com.isoft.horizon.models.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.SecondaryTable;

@Getter
@Setter
public class UserDTO {
    private Long id;

    private String email;

    private String password;

    private PersonDTO userData;

    private Role role;

    public UserDTO(String email, String password, PersonDTO userData, Role role) {
        this.email = email;
        this.password = password;
        this.userData = userData;
        this.role = role;
    }
}
