package drools.spring.example.model.dto;

public class DiseaseDto {
    private String name;
    private Number symptoms;
    private Number specificSymptoms;

    public DiseaseDto() {
    }

    public DiseaseDto(String name, Number symptoms, Number specificSymptoms) {
        this.name = name;
        this.symptoms = symptoms;
        this.specificSymptoms = specificSymptoms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Number getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(Number symptoms) {
        this.symptoms = symptoms;
    }

    public Number getSpecificSymptoms() {
        return specificSymptoms;
    }

    public void setSpecificSymptoms(Number specificSymptoms) {
        this.specificSymptoms = specificSymptoms;
    }
}
