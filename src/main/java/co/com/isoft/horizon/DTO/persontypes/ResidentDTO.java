package co.com.isoft.horizon.DTO.persontypes;

import co.com.isoft.horizon.DTO.PersonDTO;
import co.com.isoft.horizon.models.Person;
import co.com.isoft.horizon.models.Resident;

import java.util.Date;

public class ResidentDTO extends PersonDTO {
  public ResidentDTO(String name, String surname, String phoneNumber, Date birthDate) {
    super(name, surname, phoneNumber, birthDate);
  }

  @Override
  public Person toEntity() {
    return new Resident(name, surname, phoneNumber, birthDate);
  }
}
