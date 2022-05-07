package co.com.isoft.horizon.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public abstract class PersonDTO {
    private Long id;

    private String name;
    private String surname;

    private String phoneNumber;
    private Date birthDate;

    public PersonDTO(String name, String surname, String phoneNumber, Date birthDate) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }
}
