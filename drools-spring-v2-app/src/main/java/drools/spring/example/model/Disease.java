package drools.spring.example.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DISEASE_ID")
    private long id;

    private String name;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "GENERAL_SYMPTOMS_DISEASE",
            joinColumns = {@JoinColumn(name = "DISEASE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "SYMPTOM_ID")})
    private List<Symptom> generalSymptoms;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "SPECIFIC_SYMPTOMS_DISEASE",
            joinColumns = {@JoinColumn(name = "DISEASE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "SYMPTOM_ID")})
    private List<Symptom> specificSymptoms;

    public Disease() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Symptom> getGeneralSymptoms() {
        return generalSymptoms;
    }

    public void setGeneralSymptoms(List<Symptom> generalSymptoms) {
        this.generalSymptoms = generalSymptoms;
    }

    public List<Symptom> getSpecificSymptoms() {
        return specificSymptoms;
    }

    public void setSpecificSymptoms(List<Symptom> specificSymptoms) {
        this.specificSymptoms = specificSymptoms;
    }
}

