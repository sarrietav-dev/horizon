package co.com.isoft.horizon.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;
    @ManyToOne
    private PQRS pqrs;

    @ManyToOne
    private Person createdBy;

    public Reply(String content, PQRS pqrs, Person createdBy) {
        this.content = content;
        this.pqrs = pqrs;
        this.createdBy = createdBy;
    }

    protected Reply() {
    }
}
