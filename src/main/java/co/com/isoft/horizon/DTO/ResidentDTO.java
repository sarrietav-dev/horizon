package co.com.isoft.horizon.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ResidentDTO extends PersonDTO {
    @JsonIgnoreProperties(value = "residents")
    private ApartmentDTO address;

    public ResidentDTO(String name, String surname, String phoneNumber, Date birthDate) {
        super(name, surname, phoneNumber, birthDate);
    }
}
