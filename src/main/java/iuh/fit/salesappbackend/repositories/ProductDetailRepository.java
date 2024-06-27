package iuh.fit.salesappbackend.repositories;

import iuh.fit.salesappbackend.models.Color;
import iuh.fit.salesappbackend.models.Product;
import iuh.fit.salesappbackend.models.ProductDetail;
import iuh.fit.salesappbackend.models.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
    boolean existsByColorAndProductAndSize(Product product, Size size, Color color);
    List<ProductDetail> findByProductId(Long productId);
}