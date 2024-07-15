package iuh.fit.salesappbackend.service.impl;

import iuh.fit.salesappbackend.dtos.requests.ChangePasswordRequest;
import iuh.fit.salesappbackend.dtos.responses.LoginResponse;
import iuh.fit.salesappbackend.exceptions.DataNotFoundException;
import iuh.fit.salesappbackend.models.Token;
import iuh.fit.salesappbackend.models.User;
import iuh.fit.salesappbackend.models.UserDetail;
import iuh.fit.salesappbackend.repositories.TokenRepository;
import iuh.fit.salesappbackend.repositories.UserRepository;
import iuh.fit.salesappbackend.service.interfaces.UserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final JwtServiceImpl jwtService;

    public UserServiceImpl(JpaRepository<User, Long> repository, UserRepository userRepository, PasswordEncoder passwordEncoder, TokenRepository tokenRepository, JwtServiceImpl jwtService) {
        super(repository, User.class);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
        this.jwtService = jwtService;
    }
    @Override
    public LoginResponse changePassword(ChangePasswordRequest changePasswordRequest) throws DataNotFoundException {
        User user = userRepository.findByEmail(changePasswordRequest.getEmail())
                .orElseThrow(() -> new DataNotFoundException("User not found"));
        if (!passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())) {
            throw new DataNotFoundException("Old password is incorrect");
        }
        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        userRepository.save(user);
        List<Token> tokens = tokenRepository.findAllByUserOrderByExpiredDateDesc(user);
        if (!tokens.isEmpty()) {
            tokenRepository.deleteAll(tokens);
        }
        UserDetail userDetail = new UserDetail(user);
        Token newToken = new Token();
        newToken.setUser(user);
        newToken.setAccessToken(jwtService.generateToken(userDetail));
        newToken.setRefreshToken(jwtService.generateRefreshToken(new HashMap<>(), userDetail));
        newToken.setExpiredDate(LocalDateTime.now());
        tokenRepository.save(newToken);
        return LoginResponse.builder()
                .accessToken(newToken.getAccessToken())
                .refreshToken(newToken.getRefreshToken())
                .build();
    }


}
