package drools.spring.example.service;

import drools.spring.example.controller.exception.BadRequestException;
import drools.spring.example.controller.exception.NotFoundException;
import drools.spring.example.model.DrugIngredient;
import drools.spring.example.model.Patient;
import drools.spring.example.model.dto.PatientDto;
import drools.spring.example.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository repository;

    @Autowired
    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    public List<Patient> getAll() {
        return repository.findAll();
    }

    public Patient createNew(PatientDto dto) {
        if (dto.getFirstName().trim().equals("") || dto.getLastName().trim().equals("")) {
            throw new BadRequestException("Empty fields");
        }
        Patient patient = new Patient();
        patient.setFirstName(dto.getFirstName());
        patient.setLastName(dto.getLastName());
        return repository.save(patient);
    }

    public Patient updatePatient(PatientDto dto, Long id) {
        if (dto.getFirstName().trim().equals("") || dto.getLastName().trim().equals("")) {
            throw new BadRequestException("Empty fields");
        }
        Patient patient = repository.findById(id).orElseThrow(() -> new NotFoundException("Patient not found"));
        patient.setFirstName(dto.getFirstName());
        patient.setLastName(dto.getLastName());
        return repository.save(patient);
    }

    public void deletePatient(Long id) {
        Patient patient = repository.findById(id).orElseThrow(() -> new NotFoundException("Patient not found"));
        repository.delete(patient);
    }

    public Patient getPatient(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Patient not found"));
    }

    public Patient addAllergy(Long id, DrugIngredient drugIngredient) {
        Patient patient = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Patient not found"));
        for (DrugIngredient di : patient.getAllergies()) {
            if (di.getId() == drugIngredient.getId()) {
                throw new BadRequestException("It is already ingredient");
            }
        }
        patient.getAllergies().add(drugIngredient);
        return repository.save(patient);
    }

    public void deleteAllergy(Long id, Long idDI) {
        Patient patient = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Patient not found"));
        DrugIngredient toDelete = null;
        for (DrugIngredient di : patient.getAllergies()) {
            if (di.getId() == idDI) {
                toDelete = di;
            }
        }
        if (toDelete == null) {
            throw new NotFoundException("No ingredient");
        }
        patient.getAllergies().remove(toDelete);
    }
}
