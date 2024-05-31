package iuh.fit.salesappbackend.repositories;

import iuh.fit.salesappbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}