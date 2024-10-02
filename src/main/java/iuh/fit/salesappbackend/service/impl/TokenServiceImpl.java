package iuh.fit.salesappbackend.service.impl;

import iuh.fit.salesappbackend.models.Token;
import iuh.fit.salesappbackend.models.User;
import iuh.fit.salesappbackend.repositories.TokenRepository;
import iuh.fit.salesappbackend.service.interfaces.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final TokenRepository tokenRepository;
    @Override
    public void saveToken(User user, Token token) {
        List<Token> tokens = tokenRepository.findAllByUserOrderByExpiredDateDesc(user);
        if (!tokens.isEmpty() && tokens.size() >= 2) {
            Token tokenToDelete = tokens.get(tokens.size() -1);
            tokenRepository.delete(tokenToDelete);
        }
        tokenRepository.save(token);
    }
}
