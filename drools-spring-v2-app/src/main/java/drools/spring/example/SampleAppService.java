package drools.spring.example;

import drools.spring.example.controller.exception.NotFoundException;
import drools.spring.example.model.*;
import drools.spring.example.repository.DrugRepository;
import drools.spring.example.repository.PatientRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SampleAppService {

    private final KieContainer kieContainer;

    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    public SampleAppService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public void testVisitRule() {
        Symptom symptom = new Symptom("Cesto uriniranje");
        ReasonInput input = new ReasonInput();
        input.getSymptoms().add(symptom);
        symptom = new Symptom("Mucnina i povracanje");
        input.getSymptoms().add(symptom);
        symptom = new Symptom("Zamor");
        input.getSymptoms().add(symptom);
        symptom = new Symptom("Kasalj");
        input.getSymptoms().add(symptom);
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(input);
        kieSession.getAgenda().getAgendaGroup("simptomi").setFocus();
        kieSession.fireAllRules();
        kieSession.insert(input);
        kieSession.getAgenda().getAgendaGroup("connected").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
        System.out.println(input);
    }

    public void testDrugRule() {
        Drug drug = drugRepository.findById(2L).orElseThrow(NotFoundException::new);
        Patient patient = patientRepository.findById(1L).orElseThrow(NotFoundException::new);
        DrugValidationInput input = new DrugValidationInput(drug.getIngredientList(), patient.getAllergies());
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(input);
        kieSession.getAgenda().getAgendaGroup("drug").setFocus();
        kieSession.fireAllRules();
    }
}
