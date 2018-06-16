package drools.spring.example;

import drools.spring.example.model.Symptom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleAppController {

    private final SampleAppService sampleService;

    @Autowired
    public SampleAppController(SampleAppService sampleService) {
        this.sampleService = sampleService;
    }

    @RequestMapping(value = "/test3", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Symptom> test3() {
        sampleService.testReport();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
