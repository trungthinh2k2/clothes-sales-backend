package iuh.fit.salesappbackend.repositories;

import iuh.fit.salesappbackend.models.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, Long> {
}