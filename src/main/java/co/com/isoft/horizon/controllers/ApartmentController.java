package co.com.isoft.horizon.controllers;

import co.com.isoft.horizon.models.Apartment;
import co.com.isoft.horizon.repositories.ApartamentoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApartmentController {
    final ApartamentoRepo apartamentoRepo;

    public ApartmentController(ApartamentoRepo apartamentoRepo) {
        this.apartamentoRepo = apartamentoRepo;
    }

    @PostMapping("/api/apartamentos")
    public Apartment crearApartamento(@RequestBody Apartment apartamento) {
        return apartamentoRepo.save(apartamento);
    }
}
