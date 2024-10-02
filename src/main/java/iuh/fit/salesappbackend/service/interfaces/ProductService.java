package iuh.fit.salesappbackend.service.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import iuh.fit.salesappbackend.dtos.requests.ProductDto;
import iuh.fit.salesappbackend.dtos.responses.PageResponse;
import iuh.fit.salesappbackend.dtos.responses.ProductResponse;
import iuh.fit.salesappbackend.exceptions.DataExistsException;
import iuh.fit.salesappbackend.exceptions.DataNotFoundException;
import iuh.fit.salesappbackend.models.Product;
import org.springframework.data.jpa.repository.EntityGraph;

public interface ProductService extends BaseService<Product, Long> {
    Product save(ProductDto productDto) throws DataExistsException, DataNotFoundException;

    ProductResponse findProductById(Long id) throws DataNotFoundException;

    PageResponse<?> getProductsForUserRole(int pageNo, int pageSize, String[] search, String[] sort) throws JsonProcessingException;
}
