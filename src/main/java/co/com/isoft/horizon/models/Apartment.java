package co.com.isoft.horizon.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class Apartment {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String tower;
  private String stories;
  private String number;

  @ManyToOne
  @JoinColumn(name = "person_id")
  private Proprietary proprietary;

  @OneToMany(mappedBy = "address")
  private List<Resident> residents;

  protected Apartment() {}
}
