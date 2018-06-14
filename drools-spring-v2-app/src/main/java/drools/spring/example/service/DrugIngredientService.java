package drools.spring.example.service;

import drools.spring.example.controller.exception.BadRequestException;
import drools.spring.example.model.DrugIngredient;
import drools.spring.example.model.dto.DrugIngredientDto;
import drools.spring.example.repository.DrugIngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugIngredientService {
    private final DrugIngredientRepository repository;

    @Autowired
    public DrugIngredientService(DrugIngredientRepository repository) {
        this.repository = repository;
    }

    public List<DrugIngredient> getAllDI() {
        return repository.findAll();
    }

    public DrugIngredient createNewDI(DrugIngredientDto diDto) {
        if (diDto.getName() == null || diDto.getName().trim().equals("")) {
            throw new BadRequestException("Empty input");
        }
        DrugIngredient drugIngredient = new DrugIngredient();
        drugIngredient.setIngredient(diDto.getName());
        return repository.save(drugIngredient);
    }


    public DrugIngredient updateIngredient(Long id, DrugIngredientDto diDto) {
        DrugIngredient drugIngredient = repository.findById(id).orElseThrow(() -> new BadRequestException("No di with id"));
        if (diDto.getName() == null || diDto.getName().trim().equals("")) {
            throw new BadRequestException("Empty input");
        }
        drugIngredient.setIngredient(diDto.getName());
        return drugIngredient;
    }

    public void deleteDI(Long id) {
        DrugIngredient drugIngredient = repository.findById(id).orElseThrow(() -> new BadRequestException("No di with id"));
        repository.delete(drugIngredient);
    }
}
