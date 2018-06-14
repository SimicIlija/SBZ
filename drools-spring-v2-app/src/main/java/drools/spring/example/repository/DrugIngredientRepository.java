package drools.spring.example.repository;

import drools.spring.example.model.DrugIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugIngredientRepository extends JpaRepository<DrugIngredient, Long> {
}
