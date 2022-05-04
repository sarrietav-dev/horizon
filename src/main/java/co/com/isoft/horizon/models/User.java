package co.com.isoft.horizon.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "auth_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String password;

    @OneToOne
    private Person userData;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    protected User() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
