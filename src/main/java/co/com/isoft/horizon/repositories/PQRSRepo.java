package co.com.isoft.horizon.repositories;

import co.com.isoft.horizon.models.PQRS;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PQRSRepo extends JpaRepository<PQRS, Long> {
  Page<PQRS> getAllByTitleContainingIgnoreCase(String title, Pageable pageable);
}
