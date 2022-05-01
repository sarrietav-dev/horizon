package co.com.isoft.horizon.models;

import lombok.AllArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
public class Resident extends User {
    @ManyToOne
    protected Apartment address;

    @OneToMany(mappedBy = "user")
    private List<PQRS> pqrs;

    public Resident(String id, String name, String surname, String email, String phoneNumber, Date birthDate, String password, List<PQRS> pqrs, Apartment address) {
        super(id, name, surname, email, phoneNumber, birthDate, password);
        this.address = address;
        this.pqrs = pqrs;
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
