package co.com.isoft.horizon.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class Proprietary extends Person {
    @OneToMany(mappedBy = "proprietary")
    private List<Apartment> properties;

    @OneToMany(mappedBy = "user")
    private List<PQRS> pqrs;

    public Proprietary(String id, String name, String surname, String phoneNumber, Date birthDate, User user) {
        super(id, name, surname, phoneNumber, birthDate, user);
    }

    protected Proprietary() {
    }

    public List<Apartment> getProperties() {
        return properties;
    }

    public void setProperties(List<Apartment> properties) {
        this.properties = properties;
    }

    public List<PQRS> getPqrs() {
        return pqrs;
    }

    public void setPqrs(List<PQRS> pqrs) {
        this.pqrs = pqrs;
    }
}
