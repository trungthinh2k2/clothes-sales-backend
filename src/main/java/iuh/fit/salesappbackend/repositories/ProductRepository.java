package iuh.fit.salesappbackend.repositories;

import iuh.fit.salesappbackend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
    boolean existsByProductName(String productName);
>>>>>>> ae3eca8 (update service and upload image to S3, Cloudinary)
>>>>>>> fc45643 (update service and upload image to S3, Cloudinary)
}