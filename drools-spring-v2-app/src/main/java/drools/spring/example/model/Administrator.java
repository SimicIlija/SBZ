package drools.spring.example.model;

public class Administrator extends User {
    public Administrator() {
        super();
        setUserRole(UserRole.ADMIN);
    }
}
