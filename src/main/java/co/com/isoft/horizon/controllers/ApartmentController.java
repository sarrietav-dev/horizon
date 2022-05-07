package co.com.isoft.horizon.controllers;

import co.com.isoft.horizon.DTO.ApartmentDTO;
import co.com.isoft.horizon.models.Apartment;
import co.com.isoft.horizon.repositories.ApartamentoRepo;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class ApartmentController {
    final ApartamentoRepo apartamentoRepo;

    public ApartmentController(ApartamentoRepo apartamentoRepo) {
        this.apartamentoRepo = apartamentoRepo;
    }

    @GetMapping("/api/apartamentos")
    public List<ApartmentDTO> getApartments() {
        return StreamSupport
                .stream(apartamentoRepo.findAll().spliterator(), false)
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/api/apartamentos")
    public Apartment crearApartamento(@RequestBody ApartmentDTO apartmentDTO) {
        Apartment apartment = toEntity(apartmentDTO);
        return apartamentoRepo.save(apartment);
    }

    private ApartmentDTO toDTO(Apartment entity) {
        return new ModelMapper().map(entity, ApartmentDTO.class);
    }

    private Apartment toEntity(ApartmentDTO dto) {
        return new ModelMapper().map(dto, Apartment.class);
    }
}
