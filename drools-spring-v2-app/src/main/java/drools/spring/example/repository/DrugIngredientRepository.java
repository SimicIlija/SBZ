package drools.spring.example.repository;

import drools.spring.example.model.DrugIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DrugIngredientRepository extends JpaRepository<DrugIngredient, Long> {
    @Override
    Optional<DrugIngredient> findById(Long aLong);
}
