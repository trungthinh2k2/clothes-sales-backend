package iuh.fit.salesappbackend.controllers;

import iuh.fit.salesappbackend.dtos.requests.*;
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
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/refresh-token")
    public Response refreshToken(@RequestBody @Valid String refreshToken) throws DataNotFoundException {
        return new ResponseSuccess<>(
                HttpStatus.OK.value(),
                "refresh token successfully",
                authService.refreshToken(refreshToken)
        );
    }

    @GetMapping("/send-verification-email/{email}")
    public Response sendVerificationEmail(@PathVariable String email)
            throws Exception{
        authService.sendVerificationEmail(email);
        return new ResponseSuccess<>(
                HttpStatus.OK.value(),
                "send verification email successfully",
                "check your email"
        );
    }

    @PostMapping("/verify-email-otp-reset-password")
    public Response verifyEmailOtpResetPassword(@RequestBody VerifyEmailDto verifyEmailDto)
            throws DataNotFoundException {
        authService.verifyEmailOtpResetPassword(verifyEmailDto);
        return new ResponseSuccess<>(
                HttpStatus.OK.value(),
                "verify email otp reset password successfully",
                null
        );
    }

    @PostMapping("/reset-password")
    public Response resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest)
            throws Exception{
        return new ResponseSuccess<>(
                HttpStatus.OK.value(),
                "reset password successfully",
                authService.resetPassword(resetPasswordRequest)
        );
    }

    @PostMapping("/logout")
    public Response logout(@RequestBody String accessToken)
            throws DataNotFoundException {
        authService.logout(accessToken);
        return new ResponseSuccess<>(
                HttpStatus.OK.value(),
                "logout successfully",
                null
        );
    }
}
