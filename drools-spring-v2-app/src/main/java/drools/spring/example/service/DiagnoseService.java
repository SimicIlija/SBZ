package drools.spring.example.service;

import drools.spring.example.controller.exception.NotFoundException;
import drools.spring.example.model.*;
import drools.spring.example.model.dto.DiseaseDto;
import drools.spring.example.model.dto.SymptomsDto;
import drools.spring.example.repository.DiseaseRepository;
import drools.spring.example.repository.DrugRepository;
import drools.spring.example.repository.PatientRepository;
import drools.spring.example.repository.VisitRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiagnoseService {

    private final PatientRepository patientRepository;
    private final VisitRepository visitRepository;
    private final DiseaseRepository diseaseRepository;
    private final DrugRepository drugRepository;
    private final KieContainer kieContainer;

    @Autowired
    public DiagnoseService(PatientRepository patientRepository, VisitRepository visitRepository, DiseaseRepository diseaseRepository, DrugRepository drugRepository, KieContainer kieContainer) {
        this.patientRepository = patientRepository;
        this.visitRepository = visitRepository;
        this.diseaseRepository = diseaseRepository;
        this.drugRepository = drugRepository;
        this.kieContainer = kieContainer;
    }

    public List<Disease> getPossibleDis(Long userId, SymptomsDto symptomsDto) {
        Patient patient = patientRepository.findById(userId).orElseThrow(() -> new NotFoundException("ne postoji pacijent"));
        List<Visit> previousVisits = visitRepository.findByPatient_Id(patient.getId());
        ReasonInput input = new ReasonInput();
        input.setSymptoms(symptomsDto.getSymptoms());
        input.setPreviousVisits(previousVisits);
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(input);
        kieSession.getAgenda().getAgendaGroup("simptomi").setFocus();
        kieSession.fireAllRules();
        kieSession.insert(input);
        kieSession.getAgenda().getAgendaGroup("diagnose").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
        List<Disease> retVal = new ArrayList<>();
        retVal.add(input.getFirstDisease());
        retVal.add(input.getThirdDisease());
        retVal.addAll(input.getSecondDiseases());
        return retVal;
    }

    public List<DiseaseDto> getConnected(Long userId, SymptomsDto symptomsDto) {
        Patient patient = patientRepository.findById(userId).orElseThrow(() -> new NotFoundException("ne postoji pacijent"));
        List<Visit> previousVisits = visitRepository.findByPatient_Id(patient.getId());
        ReasonInput input = new ReasonInput();
        input.setSymptoms(symptomsDto.getSymptoms());
        input.setPreviousVisits(previousVisits);
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(input);
        kieSession.getAgenda().getAgendaGroup("simptomi").setFocus();
        kieSession.fireAllRules();
        kieSession.insert(input);
        kieSession.getAgenda().getAgendaGroup("connected").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
        return input.getConnectedDisease();
    }

    public SymptomsDto findSymptoms(Long diseaseId) {
        Disease disease = diseaseRepository.findById(diseaseId).orElseThrow(() -> new NotFoundException("nije nadjena bolest"));
        DiseaseInput input = new DiseaseInput();
        input.setDisease(disease);
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(input);
        kieSession.getAgenda().getAgendaGroup("bolesti").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
        return input.getSymptoms();
    }

    public boolean validate(Long drugId, Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new NotFoundException("Ne postoji pacijent"));
        Drug drug = drugRepository.findById(drugId).orElseThrow(() -> new NotFoundException("Nema leka"));
        DrugValidationInput input = new DrugValidationInput(drug.getIngredientList(), patient.getAllergies());
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(input);
        kieSession.getAgenda().getAgendaGroup("drug").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
        return input.isResult();
    }

    public List<Visit> getVisits(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new NotFoundException("ne postoji pacijent"));
        return visitRepository.findByPatient_Id(patient.getId());
    }

    public Visit saveVisit(Visit visit) {
        return visitRepository.save(visit);
    }
}
