package iuh.fit.salesappbackend.dtos.responses;

import iuh.fit.salesappbackend.models.Product;
import iuh.fit.salesappbackend.models.ProductDetail;
import iuh.fit.salesappbackend.models.ProductImage;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
public class ProductResponse {
    private Product product;
    private List<ProductImage> productImages;
    private List<ProductDetail> productDetails;
}
