package co.com.isoft.horizon.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApartmentDTO {
    protected String tower;
    protected String stories;
    protected String number;

    protected List<ResidentDTO> residents;
}
