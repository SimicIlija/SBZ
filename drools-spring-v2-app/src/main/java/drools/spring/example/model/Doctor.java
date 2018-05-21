package drools.spring.example.model;

public class Doctor extends User{
    public Doctor() {
        super();
        setUserRole(UserRole.DOCTOR);
    }
}
