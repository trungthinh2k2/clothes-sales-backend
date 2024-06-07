package iuh.fit.salesappbackend.repositories;

import iuh.fit.salesappbackend.models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
    boolean existsByProviderName(String providerName);
>>>>>>> ae3eca8 (update service and upload image to S3, Cloudinary)
>>>>>>> fc45643 (update service and upload image to S3, Cloudinary)
}