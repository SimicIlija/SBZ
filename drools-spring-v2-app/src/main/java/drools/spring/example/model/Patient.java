package drools.spring.example.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PATIENT_ID")
    private long id;

    private String firstName;

    private String lastName;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "ALLERGIES",
            joinColumns = {@JoinColumn(name = "PATIENT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "DI_ID")})
    private List<DrugIngredient> allergies;

    public Patient() {
        allergies = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<DrugIngredient> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<DrugIngredient> allergies) {
        this.allergies = allergies;
    }
}

