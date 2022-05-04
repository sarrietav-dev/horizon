package co.com.isoft.horizon.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class PQRS {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    private String category;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    private Float rating;

    @OneToMany(mappedBy = "pqrs")
    private List<Reply> replies;

    public PQRS(String title, String description, String category, Person person) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.person = person;
    }

    protected PQRS() {
    }
}
