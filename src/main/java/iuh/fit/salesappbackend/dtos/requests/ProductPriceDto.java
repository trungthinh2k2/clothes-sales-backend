package iuh.fit.salesappbackend.dtos.requests;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ProductPriceDto {
    @NotNull(message = "Discount must not be null")
    @Range(min = 0, max = 1, message = "Discount must be between 0 and 1")
    private Float discount;
    @NotNull(message = "expiredDate must not be null")
    @Future(message = "Expired date must be greater than current date")
    private LocalDateTime expiredDate;
    private String note;
    @NotNull(message = "Product id must not be null")
    private Long productId;
}
