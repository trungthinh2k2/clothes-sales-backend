package iuh.fit.salesappbackend.dtos.responses.products;

import com.fasterxml.jackson.annotation.JsonFormat;
import iuh.fit.salesappbackend.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductUserResponse {
    private Product product;
    private Float discount;
    private Double discountedPrice;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expiredDate;

}
