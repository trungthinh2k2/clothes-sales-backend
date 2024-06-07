package iuh.fit.salesappbackend.mappers;

import iuh.fit.salesappbackend.dtos.requests.ProviderDto;
import iuh.fit.salesappbackend.exceptions.DataExistsException;
import iuh.fit.salesappbackend.models.Provider;
import iuh.fit.salesappbackend.models.enums.Status;
import iuh.fit.salesappbackend.service.interfaces.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProviderMapper {

    private final ProviderService providerService;
    private final AddressMapper addressMapper;

    public Provider providerDto2Provider(ProviderDto providerDto) throws DataExistsException {
        providerService.checkExistsProviderName(providerDto.getProviderName());

        Provider provider = new Provider();
        provider.setProviderName(providerDto.getProviderName());
        provider.setAddress(addressMapper.addressDto2Address(providerDto.getAddress()));
        provider.setEmail(providerDto.getEmail());
        provider.setPhoneNumber(providerDto.getPhoneNumber());
        provider.setStatus(Status.ACTIVE);
        return provider;
    }
}
