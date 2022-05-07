package co.com.isoft.horizon.DTO;

import co.com.isoft.horizon.models.Person;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;

    private String email;
    private String password;

    private Person userData;

    public UserDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
