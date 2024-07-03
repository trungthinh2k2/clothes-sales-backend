package iuh.fit.salesappbackend.repositories;

import iuh.fit.salesappbackend.models.VoucherUsages;
import iuh.fit.salesappbackend.models.id_classes.UserVoucherId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherUsagesRepository extends JpaRepository<VoucherUsages, UserVoucherId> {
}
