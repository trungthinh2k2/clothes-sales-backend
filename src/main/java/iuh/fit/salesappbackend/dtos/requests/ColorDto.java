package iuh.fit.salesappbackend.dtos.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ColorDto {
    @NotBlank(message = "Color name must not be blank")
    private String colorName;

    @JsonCreator
    public ColorDto(String colorName) {
        this.colorName = colorName;
    }
}
