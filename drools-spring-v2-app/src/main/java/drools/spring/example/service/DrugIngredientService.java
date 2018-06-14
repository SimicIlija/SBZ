package drools.spring.example.service;

import drools.spring.example.controller.exception.BadRequestException;
import drools.spring.example.model.Drug;
import drools.spring.example.model.DrugIngredient;
import drools.spring.example.model.Patient;
import drools.spring.example.model.dto.DrugIngredientDto;
import drools.spring.example.repository.DrugIngredientRepository;
import drools.spring.example.repository.DrugRepository;
import drools.spring.example.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugIngredientService {
    private final DrugIngredientRepository repository;

    private final PatientRepository patientRepository;

    private final DrugRepository drugRepository;

    @Autowired
    public DrugIngredientService(DrugIngredientRepository repository, PatientRepository patientRepository, DrugRepository drugRepository) {
        this.repository = repository;
        this.patientRepository = patientRepository;
        this.drugRepository = drugRepository;
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
        return repository.save(drugIngredient);
    }

    public void deleteDI(Long id) {
        DrugIngredient drugIngredient = repository.findById(id).orElseThrow(() -> new BadRequestException("No di with id"));
        List<Drug> drugs = drugRepository.findAll();
        List<Patient> patients = patientRepository.findAll();
        for(Drug drug:drugs){
            if(drug.getIngredientList().contains(drugIngredient)){
                drug.getIngredientList().remove(drugIngredient);
                drugRepository.save(drug);
            }
        }
        for(Patient patient:patients){
            if(patient.getAllergies().contains(drugIngredient)){
                patient.getAllergies().remove(drugIngredient);
                patientRepository.save(patient);
            }
        }
        repository.delete(drugIngredient);
    }
}
