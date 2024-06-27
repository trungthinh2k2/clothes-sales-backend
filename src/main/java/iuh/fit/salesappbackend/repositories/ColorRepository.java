package iuh.fit.salesappbackend.repositories;

import iuh.fit.salesappbackend.models.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, Long> {
    boolean existsByColorName(String colorName);
}