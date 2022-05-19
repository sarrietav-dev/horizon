package co.com.isoft.horizon.repositories;

import co.com.isoft.horizon.models.PQRS;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PQRSRepo extends JpaRepository<PQRS, Long> {}
