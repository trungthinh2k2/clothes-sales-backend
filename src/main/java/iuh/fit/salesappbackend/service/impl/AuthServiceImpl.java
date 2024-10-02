package iuh.fit.salesappbackend.service.impl;

import iuh.fit.salesappbackend.dtos.requests.LoginRequestDto;
import iuh.fit.salesappbackend.dtos.requests.ResetPasswordRequest;
import iuh.fit.salesappbackend.dtos.requests.UserRegisterDto;
import iuh.fit.salesappbackend.dtos.requests.VerifyEmailDto;
import iuh.fit.salesappbackend.dtos.responses.LoginResponse;
import iuh.fit.salesappbackend.exceptions.DataExistsException;
import iuh.fit.salesappbackend.exceptions.DataNotFoundException;
import iuh.fit.salesappbackend.models.Token;
import iuh.fit.salesappbackend.models.User;
import iuh.fit.salesappbackend.models.UserDetail;
import iuh.fit.salesappbackend.models.enums.Role;
import iuh.fit.salesappbackend.repositories.TokenRepository;
import iuh.fit.salesappbackend.repositories.UserRepository;
import iuh.fit.salesappbackend.service.interfaces.AuthService;
import iuh.fit.salesappbackend.service.interfaces.EmailService;
import iuh.fit.salesappbackend.service.interfaces.JwtService;
import iuh.fit.salesappbackend.service.interfaces.TokenService;
import iuh.fit.salesappbackend.utils.EmailDetails;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    private final TokenService tokenService;

    @Override
    public void register(UserRegisterDto userRegisterDto) throws DataExistsException, MessagingException {
        User user = mapToUser(userRegisterDto);
        userRepository.save(user);
        EmailDetails emailDetails = EmailDetails.builder()
                .msgBody("Your OTP is: " + user.getOtp())
                .subject("Mã OTP xác thực đăng ký tài khoản")
                .recipient(user.getEmail())
                .build();

        emailService.sendHtmlMail(emailDetails);
    }

    @Override
    public LoginResponse login(LoginRequestDto loginRequestDto) throws DataNotFoundException {
        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();
        User user = userRepository.findByEmail(email). orElseThrow(() ->
            new DataNotFoundException("email is not exists")
        );
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new DataNotFoundException("password is incorrect");
        }
        UserDetail userDetail = new UserDetail(user);
        Token token = new Token();
        token.setAccessToken(jwtService.generateToken(userDetail));
        token.setRefreshToken(jwtService.generateRefreshToken(new HashMap<>(), userDetail));
        token.setUser(user);
        token.setExpiredDate(LocalDateTime.now());
        tokenService.saveToken(user, token);
        return LoginResponse.builder()
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .build();
    }

    @Override
    public LoginResponse verifyEmail(VerifyEmailDto verifyEmailDto) throws DataNotFoundException {
        String email = verifyEmailDto.getEmail();
        User user = userRepository.findByEmail(email). orElseThrow(() ->
                new DataNotFoundException("email is not exists")
        );
        if (verifyEmailDto.getOtp().equals(user.getOtp())) {
            user.setVerify(true);
            userRepository.save(user);
        } else {
            throw new DataNotFoundException("OTP is not correct");
        }
        UserDetail userDetail = new UserDetail(user);
        Token token = new Token();
        token.setAccessToken(jwtService.generateToken(userDetail));
        token.setRefreshToken(jwtService.generateRefreshToken(new HashMap<>(), userDetail));
        token.setUser(user);
        token.setExpiredDate(LocalDateTime.now());
        tokenService.saveToken(user, token);
        return LoginResponse.builder()
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .build();
    }

    @Override
    public LoginResponse refreshToken(String refreshToken) throws DataNotFoundException {
        Token token = tokenRepository.findByRefreshToken(refreshToken).orElseThrow(() ->
                new DataNotFoundException("refresh token is not exists")
        );
        String email = jwtService.extractEmail(refreshToken);
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new DataNotFoundException("user is not exists")
        );
        UserDetail userDetail = new UserDetail(user);
        if (!jwtService.validateRefreshToken(refreshToken, userDetail)) {
            tokenRepository.delete(token);
            throw new DataNotFoundException("refresh token is incorrect");
        }
        token.setAccessToken(jwtService.generateToken(userDetail));
        token.setExpiredDate(LocalDateTime.now());
        tokenRepository.save(token);
        return LoginResponse.builder()
                .accessToken(token.getAccessToken())
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public void sendVerificationEmail(String email)
            throws MessagingException, DataNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new DataNotFoundException("email is not exists")
        );
        user.setOtpResetPassword(getOtp());
        userRepository.save(user);
        EmailDetails emailDetails = EmailDetails.builder()
                .msgBody("Your OTP is: " + user.getOtpResetPassword())
                .subject("Mã OTP xác thực lại tài khoản")
                .recipient(user.getEmail())
                .build();

        emailService.sendHtmlMail(emailDetails);
    }

    @Override
    public void verifyEmailOtpResetPassword(VerifyEmailDto verifyEmailDto) throws DataNotFoundException {
        String email = verifyEmailDto.getEmail();
        User user = userRepository.findByEmail(email). orElseThrow(() ->
                new DataNotFoundException("email is not exists")
        );
        if (verifyEmailDto.getOtp().equals(user.getOtpResetPassword())) {
            user.setVerify(true);
            userRepository.save(user);
        } else {
            throw new DataNotFoundException("OTP is not correct");
        }
    }

    @Override
    public LoginResponse resetPassword(ResetPasswordRequest resetPasswordRequest) throws DataNotFoundException {
        String email = resetPasswordRequest.getEmail();
        User user = userRepository.findByEmail(email). orElseThrow(() ->
                new DataNotFoundException("email is not exists")
        );
        if (user.getOtpResetPassword() != null && user.getOtpResetPassword().equals(resetPasswordRequest.getOtpResetPassword())) {
            user.setPassword(passwordEncoder.encode(resetPasswordRequest.getNewPassword()));
            user.setOtpResetPassword(null);
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
        } else {
            throw new DataNotFoundException("OTP Reset is not correct");
        }
    }

    @Override
    public void logout(String accessToken) throws DataNotFoundException {
        Token token = tokenRepository.findByAccessToken(accessToken).orElseThrow(() ->
                new DataNotFoundException("access token is not exists")
        );
        tokenRepository.delete(token);
    }

    private User mapToUser(UserRegisterDto userRegisterDto) throws DataExistsException {
        Optional<User> user = userRepository.findByEmail(userRegisterDto.getEmail());
        User userExists = new User();
        if(user.isPresent()) {
            if(user.get().isVerify()) {
                throw new DataExistsException("email already exists");
            }
            userExists = user.get();
        }
        String otp = getOtp();
        User userRs = User.builder()
                .email(userRegisterDto.getEmail())
                .password(passwordEncoder.encode(userRegisterDto.getPassword()))
                .name(userRegisterDto.getName())
                .role(Role.ROLE_USER)
                .otp(otp)
                .phoneNumber(userRegisterDto.getPhone())
                .build();
        userRs.setId(userExists.getId());
        return userRs;
    }

    private String getOtp() {
        Random random = new Random();
        int randomNumber = 100000 + random.nextInt(90000);
        return String.valueOf(randomNumber);
    }
}
