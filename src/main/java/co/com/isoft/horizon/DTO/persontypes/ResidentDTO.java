package co.com.isoft.horizon.DTO.persontypes;

import co.com.isoft.horizon.DTO.PersonDTO;

import java.util.Date;

public class ResidentDTO extends PersonDTO {
    public ResidentDTO(String name, String surname, String phoneNumber, Date birthDate) {
        super(name, surname, phoneNumber, birthDate);
    }
}
