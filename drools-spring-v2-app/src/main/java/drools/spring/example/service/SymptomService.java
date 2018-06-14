package drools.spring.example.service;

import drools.spring.example.controller.exception.BadRequestException;
import drools.spring.example.controller.exception.NotFoundException;
import drools.spring.example.model.Symptom;
import drools.spring.example.model.dto.DrugIngredientDto;
import drools.spring.example.repository.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SymptomService {

    private final SymptomRepository symptomRepository;

    @Autowired
    public SymptomService(SymptomRepository symptomRepository) {
        this.symptomRepository = symptomRepository;
    }

    public List<Symptom> getAll() {
        return symptomRepository.findAll();
    }

    public Symptom createNew(DrugIngredientDto diDto) {
        if (diDto.getName() == null || diDto.getName().trim().equals("")) {
            throw new BadRequestException("Empty input");
        }
        Symptom symptom = new Symptom();
        symptom.setDescription(diDto.getName());
        symptom.setValue(0);
        return symptomRepository.save(symptom);
    }

    public Symptom updateSymptom(Long id, DrugIngredientDto diDto) {
        if (diDto.getName() == null || diDto.getName().trim().equals("")) {
            throw new BadRequestException("Empty input");
        }
        Symptom symptom = symptomRepository.findById(id).orElseThrow(() -> new NotFoundException("No symptom found"));
        symptom.setDescription(diDto.getName());
        symptom.setValue(0);
        return symptomRepository.save(symptom);
    }

    public void deleteSymptom(Long id) {
        Symptom symptom = symptomRepository.findById(id).orElseThrow(() -> new NotFoundException("No symptom found"));
        symptomRepository.delete(symptom);
    }
}
