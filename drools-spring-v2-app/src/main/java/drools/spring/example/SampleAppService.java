package drools.spring.example;

import drools.spring.example.controller.exception.NotFoundException;
import drools.spring.example.model.Disease;
import drools.spring.example.model.ReasonInput;
import drools.spring.example.model.Symptom;
import drools.spring.example.model.Visit;
import drools.spring.example.repository.SymptomRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import drools.spring.example.facts.Item;

import java.util.Calendar;
import java.util.Date;


@Service
public class SampleAppService {

	private static Logger log = LoggerFactory.getLogger(SampleAppService.class);

    private final KieContainer kieContainer;

    private final SymptomRepository symptomRepository;
   
    @Autowired
    public SampleAppService(KieContainer kieContainer, SymptomRepository symptomRepository) {
        log.info("Initialising a new example session.");
        this.kieContainer = kieContainer;
        this.symptomRepository = symptomRepository;
    }
    
    public Item getClassifiedItem(Item i) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(i);
        kieSession.fireAllRules();
        kieSession.dispose();
        return i;
    }

    public Symptom testNewRule() {
        Symptom symptom = symptomRepository.findById(1).orElseThrow(NotFoundException::new);
        Symptom symptom1 = symptomRepository.findById(2).orElseThrow(NotFoundException::new);
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(symptom);
        kieSession.insert(symptom1);
        kieSession.fireAllRules();
        kieSession.dispose();
        return symptom;
    }

    public void testVisitRule() {
        Symptom symptom = new Symptom("Cesto uriniranje");
        ReasonInput input = new ReasonInput();
        input.getSymptoms().add(symptom);
        symptom = new Symptom("Mucnina i povracanje");
        input.getSymptoms().add(symptom);
        symptom = new Symptom("Zamor");
        input.getSymptoms().add(symptom);
        symptom = new Symptom("Gubitak telesne tezine");
        input.getSymptoms().add(symptom);
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(input);
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
