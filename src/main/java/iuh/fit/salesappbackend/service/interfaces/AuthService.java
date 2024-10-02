package iuh.fit.salesappbackend.service.interfaces;

import iuh.fit.salesappbackend.dtos.requests.LoginRequestDto;
import iuh.fit.salesappbackend.dtos.requests.ResetPasswordRequest;
import iuh.fit.salesappbackend.dtos.requests.UserRegisterDto;
import iuh.fit.salesappbackend.dtos.requests.VerifyEmailDto;
import iuh.fit.salesappbackend.dtos.responses.LoginResponse;
import iuh.fit.salesappbackend.exceptions.DataExistsException;
import iuh.fit.salesappbackend.exceptions.DataNotFoundException;
import jakarta.mail.MessagingException;

public interface AuthService {
    void register(UserRegisterDto userRegisterDto) throws DataExistsException, MessagingException;
    LoginResponse login(LoginRequestDto loginRequestDto) throws DataNotFoundException;
    LoginResponse verifyEmail(VerifyEmailDto verifyEmailDto) throws DataNotFoundException;
    LoginResponse refreshToken(String refreshToken) throws DataNotFoundException;
    void sendVerificationEmail(String email) throws MessagingException, DataNotFoundException;
    void verifyEmailOtpResetPassword(VerifyEmailDto verifyEmailDto) throws DataNotFoundException;
    LoginResponse resetPassword(ResetPasswordRequest resetPasswordRequest) throws DataNotFoundException;
    void logout(String accessToken) throws DataNotFoundException;
}
