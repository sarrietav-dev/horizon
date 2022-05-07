package co.com.isoft.horizon.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ResidentDTO extends PersonDTO {
    private ApartamentoDTO address;

    public ResidentDTO(String id, String name, String surname, String phoneNumber, Date birthDate, ApartamentoDTO address) {
        super(id, name, surname, phoneNumber, birthDate);
        this.address = address;
    }
}
