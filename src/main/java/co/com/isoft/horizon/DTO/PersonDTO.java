package co.com.isoft.horizon.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public abstract class PersonDTO {
    private String id;

    private String name;
    private String surname;

    private String phoneNumber;
    private Date birthDate;
}
