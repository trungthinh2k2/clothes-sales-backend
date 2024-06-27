package iuh.fit.salesappbackend.service.interfaces;

import iuh.fit.salesappbackend.dtos.requests.ProductDto;
import iuh.fit.salesappbackend.dtos.responses.ProductResponse;
import iuh.fit.salesappbackend.exceptions.DataExistsException;
import iuh.fit.salesappbackend.exceptions.DataNotFoundException;
import iuh.fit.salesappbackend.models.Product;

public interface ProductService extends BaseService<Product, Long> {
    Product save(ProductDto productDto) throws DataExistsException, DataNotFoundException;
    ProductResponse findProductById(Long id) throws DataNotFoundException;
}
