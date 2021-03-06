package co.com.isoft.horizon.DTO;

import co.com.isoft.horizon.models.PQRS;
import co.com.isoft.horizon.models.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PqrsDTO {
  private Long id;

  private String title;
  private String description;
  private String category;
  private Date creationDate;
  private Long personId;
  private Status status;

  public PqrsDTO(String title, String description, String category) {
    this.title = title;
    this.description = description;
    this.category = category;
    this.status = Status.PENDING;
  }

  public PqrsDTO(PQRS pqrs) {
    this.title = pqrs.getTitle();
    this.category = pqrs.getCategory();
    this.description = pqrs.getDescription();
    this.id = pqrs.getId();
    this.creationDate = pqrs.getCreationDate();
    personId = pqrs.getPerson().getId();
    this.status = pqrs.getStatus();
  }
}
