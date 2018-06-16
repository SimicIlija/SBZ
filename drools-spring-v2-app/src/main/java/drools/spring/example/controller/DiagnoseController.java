package drools.spring.example.controller;

import drools.spring.example.model.dto.SymptomsDto;
import drools.spring.example.service.DiagnoseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/diagnose")
public class DiagnoseController {
    private final DiagnoseService diagnoseService;

    @Autowired
    public DiagnoseController(DiagnoseService diagnoseService) {
        this.diagnoseService = diagnoseService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDiseases(@PathVariable Long userId, SymptomsDto symptomsDto){

        return null;
    }
}
