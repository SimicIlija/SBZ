package drools.spring.example.repository;

import drools.spring.example.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    List<Visit> findByPatient_Id(Long id);
}
