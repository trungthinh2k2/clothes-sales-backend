package iuh.fit.salesappbackend.repositories;

import iuh.fit.salesappbackend.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
}