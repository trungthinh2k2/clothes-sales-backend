package iuh.fit.salesappbackend.controllers;

import iuh.fit.salesappbackend.dtos.requests.LoginRequestDto;
import iuh.fit.salesappbackend.dtos.requests.UserRegisterDto;
import iuh.fit.salesappbackend.dtos.requests.VerifyEmailDto;
import iuh.fit.salesappbackend.dtos.responses.Response;
import iuh.fit.salesappbackend.dtos.responses.ResponseSuccess;
import iuh.fit.salesappbackend.exceptions.DataExistsException;
import iuh.fit.salesappbackend.exceptions.DataNotFoundException;
import iuh.fit.salesappbackend.service.interfaces.AuthService;
import jakarta.mail.MessagingException;
import jakarta.persistence.PostRemove;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public Response register(@RequestBody UserRegisterDto userRegisterDto)
            throws Exception {
        authService.register(userRegisterDto);
        return new ResponseSuccess<>(
                HttpStatus.OK.value(),
                "Register successfully",
                "check otp in your email"
        );
    }

    @PostMapping("/login")
    public Response login(@RequestBody LoginRequestDto loginRequestDto)
            throws Exception {
        return new ResponseSuccess<>(
                HttpStatus.OK.value(),
                "Login successfully",
                authService.login(loginRequestDto)
        );
    }

    @PostMapping("/verify-email")
    public Response verify(@RequestBody @Valid VerifyEmailDto verifyEmailDto) throws DataNotFoundException {
        return new ResponseSuccess<>(
                HttpStatus.OK.value(),
                "verify email successfully",
                authService.verifyEmail(verifyEmailDto)
        );
    }
}
