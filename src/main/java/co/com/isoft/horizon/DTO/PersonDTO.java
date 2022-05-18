package co.com.isoft.horizon.DTO;

import co.com.isoft.horizon.DTO.persontypes.ResidentDTO;
import co.com.isoft.horizon.models.Person;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({@JsonSubTypes.Type(name = "Resident", value = ResidentDTO.class)})
public abstract class PersonDTO {
  protected Long id;

  protected String name;
  protected String surname;

  protected String phoneNumber;
  protected Date birthDate;

  protected UserDTO authData;

  public PersonDTO(String name, String surname, String phoneNumber, Date birthDate) {
    this.name = name;
    this.surname = surname;
    this.phoneNumber = phoneNumber;
    this.birthDate = birthDate;
  }

  public PersonDTO(Long id) {
    this.id = id;
  }

  public abstract Person toEntity();
}
