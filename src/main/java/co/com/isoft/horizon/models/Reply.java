package co.com.isoft.horizon.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@AllArgsConstructor
public class Reply {
    @Id
    private Long id;
    private String content;
    @ManyToOne
    private PQRS pqrs;

    @ManyToOne
    private User createdBy;

    protected Reply() {
    }
}
