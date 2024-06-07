package iuh.fit.salesappbackend.service.impl;

import iuh.fit.salesappbackend.models.UserVoucher;
import iuh.fit.salesappbackend.models.id_classes.UserVoucherId;
import iuh.fit.salesappbackend.service.interfaces.UserVoucherService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserVoucherServiceImpl
        extends BaseServiceImpl<UserVoucher, UserVoucherId> implements UserVoucherService {


    public UserVoucherServiceImpl(JpaRepository<UserVoucher, UserVoucherId> repository) {
        super(repository);
    }
}
