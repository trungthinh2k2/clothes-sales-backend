package iuh.fit.salesappbackend.repositories;

import iuh.fit.salesappbackend.models.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeRepository extends JpaRepository<Size, Long> {
}