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
@AllArgsConstructor
public class PQRS {
    @Id
    private Long id;

    private String title;
    private String description;
    private String category;

    @ManyToOne
    private User user;

    private Float rating;

    @OneToMany(mappedBy = "pqrs")
    private List<Reply> replies;

    protected PQRS() {
    }
}
