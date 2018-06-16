package drools.spring.example.model;

import drools.spring.example.model.dto.SymptomsDto;

public class DiseaseInput {
    private Disease disease;
    private SymptomsDto symptoms;

    public DiseaseInput() {
        symptoms = new SymptomsDto();
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public SymptomsDto getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(SymptomsDto symptoms) {
        this.symptoms = symptoms;
    }

    public void addNewSymptom(String name) {
        Symptom symptom = new Symptom(name);
        symptoms.getSymptoms().add(symptom);
    }
}
