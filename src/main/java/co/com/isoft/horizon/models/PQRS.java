package co.com.isoft.horizon.models;

import co.com.isoft.horizon.DTO.PqrsDTO;
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

    public PQRS(PqrsDTO dto) {
        this.title = dto.getTitle();
        this.category = dto.getCategory();
        this.description = dto.getDescription();
    }

    protected PQRS() {
    }
}
