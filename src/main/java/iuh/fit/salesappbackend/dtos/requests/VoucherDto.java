package iuh.fit.salesappbackend.dtos.requests;

import iuh.fit.salesappbackend.models.enums.Scope;
import iuh.fit.salesappbackend.models.enums.VoucherType;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class VoucherDto {
    @NotNull(message = "Voucher type must be not null")
    private Double maxPrice;
    @NotNull(message = "Min amount must be not null")
    private Double minAmount;
    @NotNull(message = "Discount must be not null")
    private Double discount;
    private VoucherType voucherType;
    @FutureOrPresent(message = "Expired date must be in the future or present")
    private LocalDateTime expriedDate;
    private Scope scope;
    @NotNull(message = "Quantity must be not null")
    private Integer quantity;
}
