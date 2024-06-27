package iuh.fit.salesappbackend.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductDetailDto {
    @NotNull(message = "Quantity must not be null")
    private Integer quantity;
    @NotNull(message = "Product ID must not be null")
    private Long productId;
    @NotNull(message = "Size ID must not be null")
    private Long sizeId;
    @NotNull(message = "Color ID must not be null")
    private Long colorId;
}
