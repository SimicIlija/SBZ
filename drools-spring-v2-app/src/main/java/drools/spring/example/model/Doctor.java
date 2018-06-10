package drools.spring.example.model;

import javax.persistence.Entity;

@Entity
public class Doctor extends User{
    public Doctor() {
        super();
        setUserRole(UserRole.DOCTOR);
    }
}
