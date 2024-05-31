package iuh.fit.salesappbackend.repositories;

import iuh.fit.salesappbackend.models.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
}