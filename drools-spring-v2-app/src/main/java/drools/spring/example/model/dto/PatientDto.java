package drools.spring.example.model.dto;

import javax.validation.constraints.NotNull;

public class PatientDto {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    public PatientDto() {
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
}
