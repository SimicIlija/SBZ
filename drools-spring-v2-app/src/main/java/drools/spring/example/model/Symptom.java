package drools.spring.example.model;

import javax.persistence.*;

@Entity
public class Symptom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SYMPTOM_ID")
    private long id;

    private String description;

    public Symptom() {
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

    @Override
    public String toString() {
        return "Symptom{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
