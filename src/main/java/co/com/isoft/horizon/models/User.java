package co.com.isoft.horizon.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class User {
    @Id
    private Long id;

    private String email;

    private String password;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Person userData;

    protected User() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
