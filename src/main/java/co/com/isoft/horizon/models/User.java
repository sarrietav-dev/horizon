package co.com.isoft.horizon.models;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String password;

    @OneToOne
    private Person userData;

    public User(String email, String password, Person userData) {
        this.email = email;
        this.password = password;
        this.userData = userData;
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
