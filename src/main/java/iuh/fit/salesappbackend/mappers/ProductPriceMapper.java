package iuh.fit.salesappbackend.mappers;

import iuh.fit.salesappbackend.dtos.requests.ProductPriceDto;
import iuh.fit.salesappbackend.exceptions.DataNotFoundException;
import iuh.fit.salesappbackend.models.Product;
import iuh.fit.salesappbackend.models.ProductPrice;
import iuh.fit.salesappbackend.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ProductPriceMapper {

    private final ProductService productService;

    public ProductPrice productPriceDto2ProductPrice(ProductPriceDto productPriceDto) throws DataNotFoundException {
        Product product = productService.findById(productPriceDto.getProductId())
                .orElseThrow(() -> new DataNotFoundException("Product not found"));
        Double discountedPrice = product.getPrice()* productPriceDto.getDiscount();
        return ProductPrice.builder()
                .product(product)
                .discount(productPriceDto.getDiscount())
                .discountedPrice(discountedPrice)
                .expriedDate(LocalDateTime.now())
                .note(productPriceDto.getNote())
                .build();
    }

}
