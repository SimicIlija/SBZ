package drools.spring.example.service;

import drools.spring.example.controller.exception.NotFoundException;
import drools.spring.example.model.*;
import drools.spring.example.repository.DiseaseRepository;
import drools.spring.example.repository.PatientRepository;
import drools.spring.example.repository.VisitRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {

    private final KieContainer kieContainer;

    private final VisitRepository visitRepository;

    private final DiseaseRepository diseaseRepository;

    private final PatientRepository patientRepository;

    @Autowired
    public ReportService(KieContainer kieContainer, VisitRepository visitRepository, DiseaseRepository diseaseRepository, PatientRepository patientRepository) {
        this.kieContainer = kieContainer;
        this.visitRepository = visitRepository;
        this.diseaseRepository = diseaseRepository;
        this.patientRepository = patientRepository;
    }

    private ReportInput getPatients(String groupName) {
        List<Visit> all = visitRepository.findAll();
        ReportInput input = new ReportInput();
        input.setVisits(all);
        List<Disease> diseases = diseaseRepository.findAll();
        List<Patient> patients = patientRepository.findAll();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(input);
        for (Disease disease : diseases) {
            kieSession.insert(disease);
        }
        for (Patient patient : patients) {
            kieSession.insert(patient);
        }
        kieSession.getAgenda().getAgendaGroup(groupName).setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
        return input;
    }

    private List<Patient> createPatients(List<Long> hronicni) {
        List<Patient> patients = new ArrayList<>();
        for (Long id : hronicni) {
            System.out.println(id);
            Patient patient = patientRepository.findById(id).orElseThrow(NotFoundException::new);
            patients.add(patient);
        }
        return patients;
    }

    public List<Patient> getZavisnici() {
        ReportInput input = getPatients("zavisnici");
        return createPatients(input.getZavisnici());
    }

    public List<Patient> getHronicne() {
        ReportInput input = getPatients("hronicnaOboljenja");
        return createPatients(input.getHronicni());
    }

    public List<Patient> getImunitet() {
        ReportInput input = getPatients("slabimunitet");
        return createPatients(input.getImunitet());
    }
}
