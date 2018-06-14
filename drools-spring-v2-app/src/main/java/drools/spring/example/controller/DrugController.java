package drools.spring.example.controller;

import drools.spring.example.model.Drug;
import drools.spring.example.model.DrugIngredient;
import drools.spring.example.model.dto.DrugDto;
import drools.spring.example.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/drugs")
public class DrugController {
    private final DrugService service;

    @Autowired
    public DrugController(DrugService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll() {
        List<Drug> all = service.getAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNew(@RequestBody DrugDto dto) {
        Drug drug = service.createNew(dto);
        return new ResponseEntity<Object>(drug, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody DrugDto dto) {
        Drug drug = service.update(dto, id);
        return new ResponseEntity<Object>(drug, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDrug(@PathVariable Long id) {
        Drug drug = service.getDrug(id);
        return new ResponseEntity<>(drug, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/ingredient",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addDrugIngredient(@PathVariable Long id, DrugIngredient drugIngredient) {
        Drug drug = service.addDi(id, drugIngredient);
        return new ResponseEntity<>(drug, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/ingredient/{idDI}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteDI(@PathVariable Long id, @PathVariable Long idDI) {
        service.deleteDI(id, idDI);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
