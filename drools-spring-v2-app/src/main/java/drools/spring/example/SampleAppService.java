package drools.spring.example;

import drools.spring.example.controller.exception.NotFoundException;
import drools.spring.example.model.*;
import drools.spring.example.repository.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class SampleAppService {

    private final KieContainer kieContainer;

    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private DiseaseRepository diseaseRepository;

    @Autowired
    private UserRepository userRepository;

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
        input.getSymptoms().add(new Symptom("Cesto uriniranje"));
        input.getSymptoms().add(new Symptom("Gubitak telesne tezine"));
        input.getSymptoms().add(new Symptom("Zamor"));
        input.getSymptoms().add(new Symptom("Mucnina i povracanje"));
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, -10);
        Date date = calendar.getTime();
        visit.setDate(date);
        input.getPreviousVisits().add(visit);
        input.getPreviousVisits().add(visit);
        input.getPreviousVisits().add(visit);
        input.getPreviousVisits().add(visit);
        input.getPreviousVisits().add(visit);
        input.getPreviousVisits().add(visit);
        input.getPreviousVisits().add(visit);
        input.getPreviousVisits().add(visit);
        input.getPreviousVisits().add(visit);
        input.getPreviousVisits().add(visit);
        input.getPreviousVisits().add(visit);
        input.getPreviousVisits().add(visit);
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(input);
        kieSession.getAgenda().getAgendaGroup("simptomi").setFocus();
        kieSession.fireAllRules();
        kieSession.insert(input);
        kieSession.getAgenda().getAgendaGroup("diagnose").setFocus();
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

    public void testTrecuGrupu() {
        ReasonInput input = new ReasonInput();
        Visit visit = new Visit();
        Drug drug = drugRepository.findById(1L).orElseThrow(NotFoundException::new);
        Disease disease = new Disease();
        disease.setName("Dijabetes");
        visit.setDisease(disease);
        visit.setDrug(drug);
        input.getSymptoms().add(new Symptom("Zamor"));
        input.getSymptoms().add(new Symptom("Nocturia"));
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, -10);
        Date date = calendar.getTime();
        visit.setDate(date);
        input.getPreviousVisits().add(visit);
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(input);
        kieSession.getAgenda().getAgendaGroup("simptomi").setFocus();
        kieSession.fireAllRules();
        kieSession.insert(input);
        kieSession.getAgenda().getAgendaGroup("diagnose").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
    }

    public void testReport() {
        List<Visit> all = visitRepository.findAll();
        ReportInput input = new ReportInput();
        input.setVisits(all);
        List<Disease> diseases = diseaseRepository.findAll();
        List<Patient> patients = patientRepository.findAll();
        List<User> doctors = userRepository.findByUserRole(UserRole.DOCTOR);
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(input);
        for (Disease disease : diseases) {
            kieSession.insert(disease);
        }
        for (Patient patient : patients) {
            kieSession.insert(patient);
        }
        for (User user : doctors) {
            kieSession.insert((Doctor) user);
        }
        kieSession.getAgenda().getAgendaGroup("zavisnici").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
