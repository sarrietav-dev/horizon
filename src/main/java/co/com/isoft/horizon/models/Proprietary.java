package co.com.isoft.horizon.models;

import co.com.isoft.horizon.DTO.PersonDTO;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class Proprietary extends Person {
    @OneToMany(mappedBy = "proprietary")
    private List<Apartment> properties;

    @OneToMany(mappedBy = "person")
    private List<PQRS> pqrs;

    public Proprietary(String name, String surname, String phoneNumber, Date birthDate, User authData, List<Apartment> properties) {
            super(name, surname, phoneNumber, birthDate, authData);
        this.properties = properties;
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
