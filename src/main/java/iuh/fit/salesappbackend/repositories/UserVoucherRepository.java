package iuh.fit.salesappbackend.repositories;

import iuh.fit.salesappbackend.models.UserVoucher;
import iuh.fit.salesappbackend.models.id_classes.UserVoucherId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVoucherRepository extends JpaRepository<UserVoucher, UserVoucherId> {
}