package iuh.fit.salesappbackend.service.impl;

import iuh.fit.salesappbackend.models.Category;
import iuh.fit.salesappbackend.service.interfaces.CategoryService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<Category, Long> implements CategoryService {
    public CategoryServiceImpl(JpaRepository<Category, Long> repository) {
        super(repository);
    }
}
