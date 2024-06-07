package iuh.fit.salesappbackend.mappers;

import iuh.fit.salesappbackend.dtos.requests.CategoryDto;
import iuh.fit.salesappbackend.models.Category;
import iuh.fit.salesappbackend.models.enums.Status;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category CategoryDto2Category(CategoryDto categoryDto) {
//        return Category.builder()
//                .categoryName(categoryDto.getCategoryName())
//                .status(categoryDto.setStatus()
//                .build();
        Category category = new Category();
        category.setCategoryName(categoryDto.getCategoryName());
        category.setStatus(Status.ACTIVE);
        return category;
    }
}
