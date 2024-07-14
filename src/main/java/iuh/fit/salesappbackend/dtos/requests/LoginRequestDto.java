package iuh.fit.salesappbackend.dtos.requests;

import lombok.*;

@Getter
@Setter
@Builder
public class LoginRequestDto {
    private String email;
    private String password;
}
