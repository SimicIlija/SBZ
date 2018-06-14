package drools.spring.example.controller;

import drools.spring.example.model.Symptom;
import drools.spring.example.model.User;
import drools.spring.example.model.dto.DrugIngredientDto;
import drools.spring.example.model.dto.UserDto;
import drools.spring.example.service.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/symptoms")
public class SymptomController {

    private final SymptomService symptomService;

    @Autowired
    public SymptomController(SymptomService symptomService) {
        this.symptomService = symptomService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll() {
        List<Symptom> all = symptomService.getAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNew(@RequestBody DrugIngredientDto diDto) {
        Symptom symptom = symptomService.createNew(diDto);
        return new ResponseEntity<Object>(symptom, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody DrugIngredientDto dto) {
        Symptom updatedSymptom = symptomService.updateSymptom(id, dto);
        return new ResponseEntity<Object>(updatedSymptom, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@PathVariable Long id) {
        symptomService.deleteSymptom(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
