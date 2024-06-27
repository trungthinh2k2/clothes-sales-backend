package iuh.fit.salesappbackend.service.impl;

import iuh.fit.salesappbackend.exceptions.DataExistsException;
import iuh.fit.salesappbackend.models.Color;
import iuh.fit.salesappbackend.models.Product;
import iuh.fit.salesappbackend.models.ProductDetail;
import iuh.fit.salesappbackend.models.Size;
import iuh.fit.salesappbackend.repositories.ProductDetailRepository;
import iuh.fit.salesappbackend.repositories.ProductRepository;
import iuh.fit.salesappbackend.service.interfaces.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailServiceImpl extends BaseServiceImpl<ProductDetail, Long> implements ProductDetailService {

    private ProductDetailRepository productDetailRepository;
    private ProductRepository productRepository;
    public ProductDetailServiceImpl(JpaRepository<ProductDetail, Long> repository) {
        super(repository);
    }

    @Autowired
    public void setProductDetailRepository(ProductDetailRepository productDetailRepository) {
        this.productDetailRepository = productDetailRepository;
    }
    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDetail save(ProductDetail productDetail)  {
        Product product = productDetail.getProduct();
        int quantity = product.getTotalQuantity() != null ? product.getTotalQuantity() : 0;
        product.setTotalQuantity(quantity + productDetail.getQuantity());
        productRepository.save(product);
        return super.save(productDetail);
    }


    @Override
    public void existsByAll(Product product, Size size, Color color) throws DataExistsException {
        if(productDetailRepository.existsByColorAndProductAndSize(product, size, color))
            throw new DataExistsException("Product detail already exists");
    }
}
