package co.com.isoft.horizon.models;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Administrator extends User {
    public Administrator(String id, String name, String surname, String email, String phoneNumber, Date birthDate, String password) {
        super(id, name, surname, email, phoneNumber, birthDate, password);
    }

    public Administrator() {
    }
}
