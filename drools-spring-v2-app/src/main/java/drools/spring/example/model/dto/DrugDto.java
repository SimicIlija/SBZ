package drools.spring.example.model.dto;

import drools.spring.example.model.DrugType;

public class DrugDto {
    private String name;
    private DrugType drugType;

    public DrugDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DrugType getDrugType() {
        return drugType;
    }

    public void setDrugType(DrugType drugType) {
        this.drugType = drugType;
    }
}
