package iuh.fit.salesappbackend.mappers;

import iuh.fit.salesappbackend.dtos.requests.CategoryDto;
import iuh.fit.salesappbackend.exceptions.DataExistsException;
import iuh.fit.salesappbackend.models.Category;
import iuh.fit.salesappbackend.models.enums.Status;
import iuh.fit.salesappbackend.service.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryMapper {
    private final CategoryService categoryService;

    public Category CategoryDto2Category(CategoryDto categoryDto) throws DataExistsException {
        categoryService.checkExistsCategoryName(categoryDto.getCategoryName());
        Category category = new Category();
        category.setCategoryName(categoryDto.getCategoryName());
        category.setStatus(Status.ACTIVE);
        return category;
    }
}
