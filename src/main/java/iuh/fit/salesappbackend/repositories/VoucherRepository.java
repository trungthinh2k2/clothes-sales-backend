package iuh.fit.salesappbackend.repositories;

import iuh.fit.salesappbackend.models.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {
}