package iuh.fit.salesappbackend.dtos.requests;

import lombok.*;

@Getter
@Setter
@Builder
public class VerifyEmailDto {
    private String email;
    private String otp;
}
