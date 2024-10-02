package iuh.fit.salesappbackend.dtos.requests;

import lombok.*;

@Getter
@Setter
@Builder
public class ChangePasswordRequest {
    private String email;
    private String oldPassword;
    private String newPassword;
}
