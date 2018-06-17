package drools.spring.example.controller;

import drools.spring.example.model.Disease;
import drools.spring.example.model.Doctor;
import drools.spring.example.model.Visit;
import drools.spring.example.model.dto.DiseaseDto;
import drools.spring.example.model.dto.SymptomsDto;
import drools.spring.example.service.DiagnoseService;
import drools.spring.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/diagnose")
public class DiagnoseController {
    private final DiagnoseService diagnoseService;
    private final UserService userService;

    @Autowired
    public DiagnoseController(DiagnoseService diagnoseService, UserService userService) {
        this.diagnoseService = diagnoseService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDiseases(@PathVariable Long userId, @RequestBody SymptomsDto symptomsDto) {
        List<Disease> diseases = diagnoseService.getPossibleDis(userId, symptomsDto);
        return new ResponseEntity<Object>(diseases, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/connected/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getConnected(@PathVariable Long userId, @RequestBody SymptomsDto symptomsDto) {
        List<DiseaseDto> diseases = diagnoseService.getConnected(userId, symptomsDto);
        return new ResponseEntity<Object>(diseases, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/bolesti/{diseaseId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSymptoms(@PathVariable Long diseaseId) {
        SymptomsDto dto = diagnoseService.findSymptoms(diseaseId);
        return new ResponseEntity<Object>(dto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/drug/{drugId}/patient/{patientId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> drugValidate(@PathVariable Long drugId, @PathVariable Long patientId) {
        boolean result = diagnoseService.validate(drugId, patientId);
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/history/{patientId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getVisits(@PathVariable Long patientId) {
        List<Visit> visits = diagnoseService.getVisits(patientId);
        return new ResponseEntity<Object>(visits, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/visit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('DOCTOR')")
    public ResponseEntity<?> saveVisit(@RequestBody Visit visit) {
        visit.setDate(new Date());
        Doctor doctor = (Doctor) userService.getCurrentUser();
        visit.setDoctor(doctor);
        Visit retVal = diagnoseService.saveVisit(visit);
        return new ResponseEntity<Object>(retVal, HttpStatus.OK);
    }

}
