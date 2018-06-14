package drools.spring.example.model;

import javax.persistence.Entity;

@Entity
public class Administrator extends User {
    public Administrator() {
        super();
        setUserRole(UserRole.ADMIN);
    }
}
