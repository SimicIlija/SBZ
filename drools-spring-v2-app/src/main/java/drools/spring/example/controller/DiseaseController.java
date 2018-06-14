package drools.spring.example.controller;

import drools.spring.example.model.Disease;
import drools.spring.example.model.dto.DrugIngredientDto;
import drools.spring.example.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/disease")
public class DiseaseController {

    private final DiseaseService service;

    @Autowired
    public DiseaseController(DiseaseService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll() {
        List<Disease> all = service.getAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNew(@RequestBody DrugIngredientDto diDto) {
        Disease newDisease = service.createNew(diDto);
        return new ResponseEntity<Object>(newDisease, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody DrugIngredientDto dto) {
        Disease updatedDisease = service.updateDisease(dto, id);
        return new ResponseEntity<Object>(updatedDisease, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@PathVariable Long id) {
        service.deleteDisease(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
