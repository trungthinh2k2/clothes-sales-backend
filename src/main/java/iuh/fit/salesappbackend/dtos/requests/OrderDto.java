package iuh.fit.salesappbackend.dtos.requests;

import iuh.fit.salesappbackend.models.Voucher;
import iuh.fit.salesappbackend.models.enums.DeliveryMethod;
import iuh.fit.salesappbackend.models.enums.PaymentMethod;
import jakarta.persistence.*;
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
    @NotNull(message = "Payment Method must not be null")
    private PaymentMethod paymentMethod;
    private String note;
    @NotNull(message = "Phone number must not be null")
    @Pattern(regexp = "^(\\+84|0)\\d{9}$", message = "Phone number is invalid")
    private String phoneNumber;
    @NotBlank(message = "Byer name must not be blank")
    private String buyerName;
    @NotNull(message = "Address must not be null")
    private AddressDto address;
    @NotNull(message = "User id must not be null")
    private Long userId;
    @NotNull(message = "Product order must not be null")
    private DeliveryMethod deliveryMethod;
    private Double deliveryAmount;

    private List<ProductOrderDto> productOrderDtos;
    private List<Long> vouchers;

}
