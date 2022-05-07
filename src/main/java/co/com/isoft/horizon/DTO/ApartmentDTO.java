package co.com.isoft.horizon.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApartmentDTO {
    private String tower;
    private String stories;
    private String number;

    private List<ResidentDTO> residents;
}
