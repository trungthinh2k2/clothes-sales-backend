package iuh.fit.salesappbackend.mappers;

import iuh.fit.salesappbackend.dtos.requests.AddressDto;
import iuh.fit.salesappbackend.models.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    public Address addressDto2Address(AddressDto addressDto) {
        return Address.builder()
                .street(addressDto.getStreet())
                .district(addressDto.getDistrict())
                .city(addressDto.getCity())
                .build();
    }
}
