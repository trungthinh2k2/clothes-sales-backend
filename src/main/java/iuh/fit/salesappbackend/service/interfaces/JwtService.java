package iuh.fit.salesappbackend.service.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateToken(UserDetails userDetails);
    String extractEmail(String token);
    boolean validateToken(String token, UserDetails userDetails);
}
