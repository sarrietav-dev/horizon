package co.com.isoft.horizon.DTO;

import co.com.isoft.horizon.models.PQRS;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PqrsDTO {
    private Long id;

    private String title;
    private String description;
    private String category;

    private PersonDTO person;

    private Long personId;

    public PqrsDTO(String title, String description, String category, Long personId) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.personId = personId;
    }

    public PqrsDTO(PQRS pqrs) {
        this.title = pqrs.getTitle();
        this.category = pqrs.getCategory();
        this.description = pqrs.getDescription();
    }
}
