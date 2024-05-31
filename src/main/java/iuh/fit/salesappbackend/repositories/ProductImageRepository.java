package iuh.fit.salesappbackend.repositories;

import iuh.fit.salesappbackend.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}