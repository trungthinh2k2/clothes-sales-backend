package iuh.fit.salesappbackend.dtos.requests;

import iuh.fit.salesappbackend.models.enums.DeliveryMethod;
import iuh.fit.salesappbackend.models.enums.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class OrderDto {
    @NotBlank(message = "Email must not be blank")
    private String email;
    @NotNull(message = "Payment Method must not be null")
    private PaymentMethod paymentMethod;
    private String note;
    @NotNull(message = "Phone number must not be null")
    @Pattern(regexp = "^(0)\\d{9}$", message = "Phone number is invalid")
    private String phoneNumber;
    @NotBlank(message = "Byer name must not be blank")
    private String buyerName;
    @NotNull(message = "Address must not be null")
    private AddressDto address;
    @NotNull(message = "Product order must not be null")
    private DeliveryMethod deliveryMethod;
    private Double deliveryAmount;
    private List<ProductOrderDto> productOrders;
    private List<Long> vouchers;

}
