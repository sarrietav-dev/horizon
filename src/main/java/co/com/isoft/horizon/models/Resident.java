package co.com.isoft.horizon.models;

import lombok.AllArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
public class Resident extends Person {
    @ManyToOne
    protected Apartment address;

    @OneToMany(mappedBy = "user")
    private List<PQRS> pqrs;

    public Resident(String id, String name, String surname, String phoneNumber, Date birthDate, User user, Apartment address) {
        super(id, name, surname, phoneNumber, birthDate, user);
        this.address = address;
    }

    protected Resident() {
    }

    public Apartment getAddress() {
        return address;
    }

    public void setAddress(Apartment address) {
        this.address = address;
    }

    public List<PQRS> getPqrs() {
        return pqrs;
    }

    public void setPqrs(List<PQRS> pqrs) {
        this.pqrs = pqrs;
    }
}
