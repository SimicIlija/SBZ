package drools.spring.example;

import drools.spring.example.controller.exception.NotFoundException;
import drools.spring.example.model.*;
import drools.spring.example.repository.DrugRepository;
import drools.spring.example.repository.PatientRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;


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
        ReasonInput input = new ReasonInput();
        Visit visit = new Visit();
        Drug drug = drugRepository.findById(1L).orElseThrow(NotFoundException::new);
        Disease disease = new Disease();
        disease.setName("Naziv");
        visit.setDisease(disease);
        visit.setDrug(drug);
        visit.getSymptoms().add(new Symptom("Visok pritisak"));
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, -10);
        Date date = calendar.getTime();
        visit.setDate(date);
        input.getPreviousVisits().add(visit);
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(input);
        kieSession.getAgenda().getAgendaGroup("simptomi").setFocus();
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
