package drools.spring.example.model;

import java.util.List;

public class DrugValidationInput {
    private List<DrugIngredient> drugIngredients;

    private List<DrugIngredient> allergies;

    private boolean result;

    public DrugValidationInput() {
    }

    public DrugValidationInput(List<DrugIngredient> drugIngredients, List<DrugIngredient> allergies) {
        this.drugIngredients = drugIngredients;
        this.allergies = allergies;
    }

    public List<DrugIngredient> getDrugIngredients() {
        return drugIngredients;
    }

    public void setDrugIngredients(List<DrugIngredient> drugIngredients) {
        this.drugIngredients = drugIngredients;
    }

    public List<DrugIngredient> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<DrugIngredient> allergies) {
        this.allergies = allergies;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
