package iuh.fit.salesappbackend.service.impl;

import iuh.fit.salesappbackend.dtos.requests.LoginRequestDto;
import iuh.fit.salesappbackend.dtos.requests.UserRegisterDto;
import iuh.fit.salesappbackend.dtos.requests.VerifyEmailDto;
import iuh.fit.salesappbackend.dtos.responses.LoginResponse;
import iuh.fit.salesappbackend.exceptions.DataExistsException;
import iuh.fit.salesappbackend.exceptions.DataNotFoundException;
import iuh.fit.salesappbackend.models.User;
import iuh.fit.salesappbackend.models.UserDetail;
import iuh.fit.salesappbackend.models.enums.Role;
import iuh.fit.salesappbackend.repositories.UserRepository;
import iuh.fit.salesappbackend.service.interfaces.AuthService;
import iuh.fit.salesappbackend.service.interfaces.EmailService;
import iuh.fit.salesappbackend.service.interfaces.JwtService;
import iuh.fit.salesappbackend.utils.EmailDetails;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

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
        UserDetail userDetail = new UserDetail(user);

        return LoginResponse.builder()
                .accessToken(jwtService.generateToken(userDetail))
                .refreshToken("refreshToken")
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
        return LoginResponse.builder()
                .accessToken(jwtService.generateToken(userDetail))
                .refreshToken("refreshToken")
                .build();
    }

    private User mapToUser(UserRegisterDto userRegisterDto) throws DataExistsException {
        if (userRepository.existsByEmail(userRegisterDto.getEmail())) {
            throw new DataExistsException("Email already exists");
        }
        Random random = new Random();
        int randomNumber = 100000 + random.nextInt(90000);
        String otp = String.valueOf(randomNumber);
        return User.builder()
                .email(userRegisterDto.getEmail())
                .password(passwordEncoder.encode(userRegisterDto.getPassword()))
                .name(userRegisterDto.getName())
                .role(Role.ROLE_USER)
                .otp(otp)
                .phoneNumber(userRegisterDto.getPhone())
                .build();
    }
}
