package co.com.isoft.horizon.models;

import co.com.isoft.horizon.DTO.PersonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Resident extends Person {
    @ManyToOne
    @JoinColumn(name = "address_id")
    protected Apartment address;

    @OneToMany(mappedBy = "person")
    private List<PQRS> pqrs;

    public Resident(String name, String surname, String phoneNumber, Date birthDate, User authData, Apartment address) {
        super(name, surname, phoneNumber, birthDate, authData);
        this.address = address;
    }

    public Resident(String name, String surname, String phoneNumber, Date birthDate) {
        super(name, surname, phoneNumber, birthDate);
    }

    protected Resident() {
    }

    @Override
    public Person from(PersonDTO personDTO) {
        return new Resident(personDTO.getName(), personDTO.getSurname(), personDTO.getPhoneNumber(), personDTO.getBirthDate());
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
