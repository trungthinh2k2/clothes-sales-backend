package iuh.fit.salesappbackend.mappers;

import iuh.fit.salesappbackend.dtos.requests.ProductDetailDto;
import iuh.fit.salesappbackend.exceptions.DataNotFoundException;
import iuh.fit.salesappbackend.models.Color;
import iuh.fit.salesappbackend.models.Product;
import iuh.fit.salesappbackend.models.ProductDetail;
import iuh.fit.salesappbackend.models.Size;
import iuh.fit.salesappbackend.service.interfaces.ColorService;
import iuh.fit.salesappbackend.service.interfaces.ProductDetailService;
import iuh.fit.salesappbackend.service.interfaces.ProductService;
import iuh.fit.salesappbackend.service.interfaces.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductDetailMapper {
    private final ProductService productService;
    private final SizeService sizeService;
    private final ColorService colorService;
    private final ProductDetailService productDetailService;

    public ProductDetail productDetailDto2ProductDetail(ProductDetailDto productDetailDto) throws DataNotFoundException {
        Product product = productService.findById(productDetailDto.getProductId())
                .orElseThrow(() -> new DataNotFoundException("Product not found"));
        Size size = sizeService.findById(productDetailDto.getSizeId())
                .orElseThrow(() -> new DataNotFoundException("Size not found"));
        Color color = colorService.findById(productDetailDto.getColorId())
                .orElseThrow(() -> new DataNotFoundException("Color not found"));
        return ProductDetail.builder()
                .quantity(productDetailDto.getQuantity())
                .product(product)
                .size(size)
                .color(color)
                .build();
    }
}
