package iuh.fit.salesappbackend.service.impl;

import iuh.fit.salesappbackend.exceptions.DataExistsException;
import iuh.fit.salesappbackend.models.Category;
import iuh.fit.salesappbackend.repositories.CategoryRepository;
import iuh.fit.salesappbackend.service.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<Category, Long> implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(JpaRepository<Category, Long> repository) {
        super(repository, Category.class);
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void checkExistsCategoryName(String categoryName) throws DataExistsException {
        if (categoryRepository.existsByCategoryName(categoryName)) {
            throw new DataExistsException("Category name already exists");
        }
    }
}
