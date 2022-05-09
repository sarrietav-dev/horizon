package co.com.isoft.horizon.services;

import co.com.isoft.horizon.models.PQRS;
import co.com.isoft.horizon.repositories.PQRSRepo;
import org.springframework.stereotype.Service;

@Service
public class PqrsService {
    private final PQRSRepo pqrsRepo;


    public PqrsService(PQRSRepo pqrsRepo) {
        this.pqrsRepo = pqrsRepo;
    }

    public PQRS create(PQRS pqrs) {
        pqrsRepo.save(pqrs);
        return pqrs;
    }
}
