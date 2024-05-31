package iuh.fit.salesappbackend.dtos.requests.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryDto  {
    @NotBlank(message = "Category name must be not blank")
    String categoryName;
}