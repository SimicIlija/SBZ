package drools.spring.example.controller;

import drools.spring.example.model.DrugIngredient;
import drools.spring.example.model.User;
import drools.spring.example.model.dto.DrugIngredientDto;
import drools.spring.example.model.dto.UserDto;
import drools.spring.example.service.DrugIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/drugingredient")
public class DrugIngredientController {

    private final DrugIngredientService drugIngredientService;

    @Autowired
    public DrugIngredientController(DrugIngredientService drugIngredientService) {
        this.drugIngredientService = drugIngredientService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll() {
        List<DrugIngredient> di = drugIngredientService.getAllDI();
        return new ResponseEntity<Object>(di, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createNewDrIng(@RequestBody DrugIngredientDto diDto) {
        DrugIngredient createdDi = drugIngredientService.createNewDI(diDto);
        return new ResponseEntity<Object>(createdDi, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDI(@PathVariable Long id, @RequestBody DrugIngredientDto diDto) {
        DrugIngredient updateDI = drugIngredientService.updateIngredient(id, diDto);
        return new ResponseEntity<Object>(updateDI, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteDI(@PathVariable Long id) {
        drugIngredientService.deleteDI(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
