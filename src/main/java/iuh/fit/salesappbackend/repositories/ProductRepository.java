package iuh.fit.salesappbackend.repositories;

import iuh.fit.salesappbackend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}