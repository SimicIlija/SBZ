package drools.spring.example.repository;

import drools.spring.example.model.User;
import drools.spring.example.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findByUserRole(UserRole role);
}
