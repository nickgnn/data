package my.business.data.repo;

import my.business.data.entities.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<Deal, String> {
}
