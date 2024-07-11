package iuh.fit.salesappbackend.dtos.requests;

import iuh.fit.salesappbackend.models.enums.Gender;
import iuh.fit.salesappbackend.models.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UserDto implements Serializable {
    @NotBlank(message = "Name must be not blank")
    private String name;
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Email is invalid")
    @NotBlank(message = "Email must be not blank")
    private String email;
    @NotBlank(message = "Password must be not blank")
    private String password;
    @Pattern(regexp = "^(\\+84|0)\\d{9}$", message = "Phone number is invalid")
    private String phoneNumber;
    private Gender gender;
    private Role role;
//    @VatidatorYear(min = 18, max = 60, message = "Age must be between 18 and 60")
    @Past(message = "current date must be greater than date of birth")
    private LocalDateTime dateOfBirth;
    private AddressDto address;
}