package drools.spring.example.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DRUG_ID")
    private long id;

    private String name;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "DRUG_INGREDIENTS_LIST",
            joinColumns = {@JoinColumn(name = "DRUG_ID")},
            inverseJoinColumns = {@JoinColumn(name = "DI_ID")})
    private List<DrugIngredient> ingredientList;

    @Enumerated(EnumType.STRING)
    private DrugType drugType;

    public Drug() {
        ingredientList = new ArrayList<>();
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

    public List<DrugIngredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<DrugIngredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public DrugType getDrugType() {
        return drugType;
    }

    public void setDrugType(DrugType drugType) {
        this.drugType = drugType;
    }
}
