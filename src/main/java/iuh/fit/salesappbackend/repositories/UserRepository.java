package iuh.fit.salesappbackend.repositories;

import iuh.fit.salesappbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}