package iuh.fit.salesappbackend.repositories;

import iuh.fit.salesappbackend.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}