package co.com.isoft.horizon.models;

import co.com.isoft.horizon.DTO.PersonDTO;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Administrator extends Person {
    public Administrator(String name, String surname, String phoneNumber, Date birthDate, User authData) {
        super(name, surname, phoneNumber, birthDate, authData);
    }

    public Administrator() {
    }


}
