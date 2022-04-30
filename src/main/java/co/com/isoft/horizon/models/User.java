package co.com.isoft.horizon.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@AllArgsConstructor
public abstract class User {
    @Id
    private String id;
    private String name;
    private String surname;

    @Column(unique = true)
    private String email;

    private String phoneNumber;
    private Date birthDate;
    private String password;


    protected User() {

    }
}
