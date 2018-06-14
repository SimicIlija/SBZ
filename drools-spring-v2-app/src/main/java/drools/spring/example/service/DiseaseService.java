package drools.spring.example.service;

import drools.spring.example.controller.exception.BadRequestException;
import drools.spring.example.controller.exception.NotFoundException;
import drools.spring.example.model.Disease;
import drools.spring.example.model.dto.DrugIngredientDto;
import drools.spring.example.repository.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiseaseService {
    private final DiseaseRepository repository;

    @Autowired
    public DiseaseService(DiseaseRepository repository) {
        this.repository = repository;
    }

    public List<Disease> getAll() {
        return repository.findAll();
    }

    public Disease createNew(DrugIngredientDto dto) {
        if (dto.getName() == null || dto.getName().trim().equals("")) {
            throw new BadRequestException("Empty input");
        }
        Disease disease = new Disease();
        disease.setName(dto.getName());
        return repository.save(disease);
    }

    public Disease updateDisease(DrugIngredientDto dto, Long id) {
        if (dto.getName() == null || dto.getName().trim().equals("")) {
            throw new BadRequestException("Empty input");
        }
        Disease disease = repository.findById(id).orElseThrow(() -> new NotFoundException("Disease not found"));
        disease.setName(dto.getName());
        return repository.save(disease);
    }

    public void deleteDisease(Long id) {
        Disease disease = repository.findById(id).orElseThrow(() -> new NotFoundException("Disease not found"));
        repository.delete(disease);
    }
}
