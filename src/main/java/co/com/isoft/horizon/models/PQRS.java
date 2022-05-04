package co.com.isoft.horizon.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class PQRS {
    @Id
    private Long id;

    private String title;
    private String description;
    private String category;

    @ManyToOne
    private Person user;

    private Float rating;

    @OneToMany(mappedBy = "pqrs")
    private List<Reply> replies;

    public PQRS(String title, String description, String category, Person user) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.user = user;
    }

    protected PQRS() {
    }
}
