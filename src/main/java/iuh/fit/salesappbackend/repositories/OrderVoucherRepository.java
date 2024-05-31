package iuh.fit.salesappbackend.repositories;

import iuh.fit.salesappbackend.models.OrderVoucher;
import iuh.fit.salesappbackend.models.id_classes.OrderVoucherId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderVoucherRepository extends JpaRepository<OrderVoucher, OrderVoucherId> {
}