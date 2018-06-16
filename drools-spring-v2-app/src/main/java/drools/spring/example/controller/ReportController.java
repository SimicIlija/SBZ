package drools.spring.example.controller;

import drools.spring.example.model.Patient;
import drools.spring.example.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/reports")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @RequestMapping(value = "/hronicni",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllHronicni() {
        List<Patient> patients = reportService.getHronicne();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @RequestMapping(value = "/zavisnici",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getZavisnici() {
        List<Patient> patients = reportService.getZavisnici();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @RequestMapping(value = "/imunitet",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getImunitet() {
        List<Patient> patients = reportService.getImunitet();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }
}
