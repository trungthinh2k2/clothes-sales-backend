package iuh.fit.salesappbackend.repositories;

import iuh.fit.salesappbackend.models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
}