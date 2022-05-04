package co.com.isoft.horizon.models;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Administrator extends Person {
    public Administrator(String id, String name, String surname, String phoneNumber, Date birthDate, User user) {
        super(id, name, surname, phoneNumber, birthDate, user);
    }

    public Administrator() {
    }
}
