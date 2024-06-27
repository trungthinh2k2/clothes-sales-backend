package iuh.fit.salesappbackend.service.interfaces;

import iuh.fit.salesappbackend.exceptions.DataExistsException;
import iuh.fit.salesappbackend.models.Category;

public interface CategoryService extends BaseService<Category, Long>{
    void checkExistsCategoryName(String categoryName) throws DataExistsException;
}
