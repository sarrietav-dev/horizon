package co.com.isoft.horizon.services.implementations;

import co.com.isoft.horizon.models.PQRS;
import co.com.isoft.horizon.repositories.PQRSRepo;
import co.com.isoft.horizon.services.PqrsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PqrsServiceImplementation implements PqrsService {
    private final PQRSRepo pqrsRepo;

    @Override
    public List<PQRS> getAll() {
        log.info("Getting all the PQRS");
        return (List<PQRS>) pqrsRepo.findAll();
    }

    @Override
    public PQRS save(PQRS pqrs) {
        log.info("Saving the PQRS with title {}", pqrs.getTitle());
        return pqrsRepo.save(pqrs);
    }
}
