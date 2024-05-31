package iuh.fit.salesappbackend.repositories;

import iuh.fit.salesappbackend.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}