package co.com.isoft.horizon.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Long id;

    protected String name;
    protected String surname;

    protected String phoneNumber;
    protected Date birthDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auth_data_id", nullable = false, unique = true)
    protected User authData;

    public Person(String name, String surname, String phoneNumber, Date birthDate) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }

    public Person(String name, String surname, String phoneNumber, Date birthDate, User authData) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.authData = authData;
    }

    protected Person() {

    }
}
