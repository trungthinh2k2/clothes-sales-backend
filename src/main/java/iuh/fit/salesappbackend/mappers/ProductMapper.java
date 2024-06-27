package iuh.fit.salesappbackend.mappers;

import iuh.fit.salesappbackend.dtos.requests.ProductDto;
import iuh.fit.salesappbackend.exceptions.DataNotFoundException;
import iuh.fit.salesappbackend.models.Category;
import iuh.fit.salesappbackend.models.Product;
import iuh.fit.salesappbackend.models.Provider;
import iuh.fit.salesappbackend.models.enums.ProductStatus;
import iuh.fit.salesappbackend.service.interfaces.CategoryService;
import iuh.fit.salesappbackend.service.interfaces.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final CategoryService categoryService;
    private final ProviderService providerService;


    public Product ProductDto2Product(ProductDto productDto) throws DataNotFoundException {

        Category category = categoryService.findById(productDto.getCategoryId())
                .orElseThrow(() -> new DataNotFoundException("Category not found"));

        Provider provider = providerService.findById(productDto.getProviderId())
                .orElseThrow(() -> new DataNotFoundException("Provider not found"));

        return Product.builder()
                .productName(productDto.getProductName())
                .price(productDto.getPrice())
                .description(productDto.getDescription())
                .productStatus(ProductStatus.ACTIVE)
                .category(category)
                .provider(provider)
                .build();
    }
}
