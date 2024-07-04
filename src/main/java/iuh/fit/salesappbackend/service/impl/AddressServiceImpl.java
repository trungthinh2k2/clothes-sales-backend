package iuh.fit.salesappbackend.service.impl;

import iuh.fit.salesappbackend.models.Address;
import iuh.fit.salesappbackend.service.interfaces.AddressService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl extends BaseServiceImpl<Address, Long> implements AddressService {
    public AddressServiceImpl(JpaRepository<Address, Long> repository) {
        super(repository, Address.class);
    }
}
