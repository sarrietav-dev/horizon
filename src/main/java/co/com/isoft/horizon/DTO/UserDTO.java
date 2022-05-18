package co.com.isoft.horizon.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
  private Long id;

  private String email;

  private String password;

  private PersonDTO userData;

  private String role;

  public UserDTO(String email, String password, PersonDTO userData, String role) {
    this.email = email;
    this.password = password;
    this.userData = userData;
    this.role = role;
  }
}
