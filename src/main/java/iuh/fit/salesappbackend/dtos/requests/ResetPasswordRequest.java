package iuh.fit.salesappbackend.dtos.requests;

import lombok.*;

@Getter
@Setter
@Builder
public class ResetPasswordRequest {
    private String email;
    private String otpResetPassword;
    private String newPassword;
}
