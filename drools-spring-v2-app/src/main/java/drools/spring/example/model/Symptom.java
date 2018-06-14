package drools.spring.example.model;

import javax.persistence.*;

@Entity
public class Symptom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SYMPTOM_ID")
    private long id;

    private String description;

    @Column(name = "DOUBLE_VALUE")
    private double value;

    public Symptom() {
    }

    public Symptom(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Symptom{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
