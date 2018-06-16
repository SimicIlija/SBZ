package drools.spring.example.repository;

import drools.spring.example.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Long> {
}
