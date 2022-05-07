package co.com.isoft.horizon.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String surname;

    private String phoneNumber;
    private Date birthDate;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auth_user_id")
    private User authData;

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
