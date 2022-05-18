package co.com.isoft.horizon.models;

import co.com.isoft.horizon.DTO.PqrsDTO;
import co.com.isoft.horizon.models.exceptions.ForbiddenStatusChangeException;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
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

    private Date creationDate;

    @Enumerated(EnumType.STRING)
    private Status status;

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

    public PQRS(String title, String description, String category, Date creationDate) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.creationDate = creationDate;
    }

    public PQRS(String title, String description, String category, Date creationDate, Status status) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.creationDate = creationDate;
        this.status = status;
    }

    public PQRS(PqrsDTO dto) {
        this.title = dto.getTitle();
        this.category = dto.getCategory();
        this.description = dto.getDescription();
    }

    protected PQRS() {
    }

    /**
     * Set the current status to a  new status.
     * The new status can't be one that happens before the actual status value in the hierarchy.
     *
     * @throws ForbiddenStatusChangeException if the new status comes before the new status.
     */
    public void setStatus(Status status) throws ForbiddenStatusChangeException {
        if (status.getHierarchy() < this.getStatus().getHierarchy()) {
            throw new ForbiddenStatusChangeException();
        }
        this.status = status;
    }
}
