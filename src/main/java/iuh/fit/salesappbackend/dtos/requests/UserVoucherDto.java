package iuh.fit.salesappbackend.dtos.requests;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserVoucherDto {
    private Long userId;
    private Long voucherId;
    @NotNull
    private Boolean isUsed;
}