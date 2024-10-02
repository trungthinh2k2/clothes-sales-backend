package iuh.fit.salesappbackend.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserVoucherDto {
    @NotNull(message = "User id must not be null")
    private Long userId;
    @NotNull(message = "Voucher id must not be null")
    private Long voucherId;
}