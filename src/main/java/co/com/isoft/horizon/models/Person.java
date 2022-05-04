package co.com.isoft.horizon.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@AllArgsConstructor
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
    private String surname;

    private String phoneNumber;
    private Date birthDate;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    protected Person() {

    }
}
