package iuh.fit.salesappbackend.repositories;

import iuh.fit.salesappbackend.models.OrderDetail;
import iuh.fit.salesappbackend.models.id_classes.OrderDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {
}