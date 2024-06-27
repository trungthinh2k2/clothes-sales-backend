package iuh.fit.salesappbackend.service.interfaces;

import iuh.fit.salesappbackend.exceptions.DataExistsException;
import iuh.fit.salesappbackend.models.Color;
import iuh.fit.salesappbackend.models.Product;
import iuh.fit.salesappbackend.models.ProductDetail;
import iuh.fit.salesappbackend.models.Size;

public interface ProductDetailService extends BaseService<ProductDetail, Long> {
    void existsByAll(Product product, Size size, Color color) throws DataExistsException;
}
