package drools.spring.example.model.dto;

import drools.spring.example.model.Symptom;

import java.util.ArrayList;
import java.util.List;

public class SymptomsDto {
    private List<Symptom> symptoms;

    public SymptomsDto() {
        this.symptoms = new ArrayList<>();
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }
}
