package co.com.isoft.horizon.models;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Administrator extends Person {
    public Administrator(String name, String surname, String phoneNumber, Date birthDate) {
        super(name, surname, phoneNumber, birthDate);
    }

    public Administrator() {
    }
}
