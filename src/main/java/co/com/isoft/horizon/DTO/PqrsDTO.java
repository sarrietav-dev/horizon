package co.com.isoft.horizon.DTO;

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

    public PqrsDTO(String title, String description, String category) {
        this.title = title;
        this.description = description;
        this.category = category;
    }
}
