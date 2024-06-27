package iuh.fit.salesappbackend.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductOrderDto {
    @NotNull(message = "Product detail id must not be null")
    private Long productDetailId;
    @NotNull(message = "Quantity must not be null")
    private Integer quantity;
}
