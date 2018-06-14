package drools.spring.example.repository;

import drools.spring.example.model.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SymptomRepository extends JpaRepository<Symptom, Long> {
}
