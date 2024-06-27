package iuh.fit.salesappbackend.service.impl;

import iuh.fit.salesappbackend.models.Voucher;
import iuh.fit.salesappbackend.service.interfaces.VoucherService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class VoucherServiceImpl extends BaseServiceImpl<Voucher, Long> implements VoucherService {
    public VoucherServiceImpl(JpaRepository<Voucher, Long> repository) {
        super(repository);
    }
}
