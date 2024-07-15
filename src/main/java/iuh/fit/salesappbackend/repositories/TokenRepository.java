package iuh.fit.salesappbackend.repositories;

import iuh.fit.salesappbackend.models.Token;
import iuh.fit.salesappbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    List<Token> findAllByUserOrderByExpiredDateDesc(User user);
    Optional<Token> findByRefreshToken(String refreshToken);
    boolean existsByAccessToken(String accessToken);
    boolean existsByRefreshToken(String refreshToken);
    Optional<Token> findByAccessToken(String accessToken);
}