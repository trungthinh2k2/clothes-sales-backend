package iuh.fit.salesappbackend.dtos.requests.user;

import iuh.fit.salesappbackend.models.enums.Gender;
import iuh.fit.salesappbackend.models.enums.Role;
import iuh.fit.salesappbackend.validator.ValidatorDate;
import iuh.fit.salesappbackend.validator.VatidatorYear;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UserDto {
    @ValidatorDate
    LocalDateTime createdAt;
    @ValidatorDate
    LocalDateTime updatedAt;
    @NotBlank
    String name;
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Email is invalid")
    String email;
    @NotBlank
    String password;
    @Pattern(regexp = "^(\\+84|0)\\d{9}$", message = "Phone number is invalid")
    String phoneNumber;
    @NotNull
    Gender gender;
    @NotNull
    Role role;
    @VatidatorYear(min = 18, max = 60, message = "Age must be between 18 and 60")
    LocalDateTime dateOfBirth;
}