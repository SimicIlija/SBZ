package drools.spring.example.service;

import drools.spring.example.controller.exception.BadRequestException;
import drools.spring.example.controller.exception.NotFoundException;
import drools.spring.example.model.Drug;
import drools.spring.example.model.DrugIngredient;
import drools.spring.example.model.dto.DrugDto;
import drools.spring.example.repository.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugService {
    private final DrugRepository repository;

    @Autowired
    public DrugService(DrugRepository repository) {
        this.repository = repository;
    }

    public List<Drug> getAll() {
        return repository.findAll();
    }

    public Drug createNew(DrugDto dto) {
        if (dto.getName() == null || dto.getDrugType() == null) {
            throw new BadRequestException("Empty fields");
        }
        if (dto.getName().trim().equals("")) {
            throw new BadRequestException("Empty fields");
        }
        Drug drug = new Drug();
        drug.setName(dto.getName());
        drug.setDrugType(dto.getDrugType());
        return repository.save(drug);
    }

    public Drug update(DrugDto dto, Long id) {
        if (dto.getName() == null || dto.getDrugType() == null) {
            throw new BadRequestException("Empty fields");
        }
        if (dto.getName().trim().equals("")) {
            throw new BadRequestException("Empty fields");
        }
        Drug drug = repository.findById(id).orElseThrow(() -> new NotFoundException("Not found drug"));
        drug.setName(dto.getName());
        drug.setDrugType(dto.getDrugType());
        return repository.save(drug);
    }

    public void delete(Long id) {
        Drug drug = repository.findById(id).orElseThrow(() -> new NotFoundException("Not found drug"));
        repository.delete(drug);
    }

    public Drug getDrug(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Not found drug"));
    }

    public Drug addDi(Long id, DrugIngredient drugIngredient) {
        Drug drug = repository.findById(id).orElseThrow(() -> new NotFoundException("Not found drug"));
        for (DrugIngredient di : drug.getIngredientList()) {
            if (di.getId() == drugIngredient.getId()) {
                throw new BadRequestException("It is already ingredient");
            }
        }
        drug.getIngredientList().add(drugIngredient);
        return repository.save(drug);
    }

    public void deleteDI(Long id, Long idDI) {
        Drug drug = repository.findById(id).orElseThrow(() -> new NotFoundException("Not found drug"));
        DrugIngredient toDelete = null;
        for (DrugIngredient di : drug.getIngredientList()) {
            if (di.getId() == idDI) {
                toDelete = di;
            }
        }
        if (toDelete == null) {
            throw new NotFoundException("No ingredient");
        }
        drug.getIngredientList().remove(toDelete);
    }
}
