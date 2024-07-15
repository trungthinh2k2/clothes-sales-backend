package iuh.fit.salesappbackend.service.interfaces;

import iuh.fit.salesappbackend.models.Token;
import iuh.fit.salesappbackend.models.User;

public interface TokenService {
    void saveToken(User user, Token token);
}
