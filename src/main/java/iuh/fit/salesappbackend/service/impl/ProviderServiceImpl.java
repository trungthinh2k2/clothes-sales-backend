package iuh.fit.salesappbackend.service.impl;

import iuh.fit.salesappbackend.exceptions.DataExistsException;
import iuh.fit.salesappbackend.models.Provider;
import iuh.fit.salesappbackend.repositories.ProviderRepository;
import iuh.fit.salesappbackend.service.interfaces.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProviderServiceImpl extends BaseServiceImpl<Provider, Long> implements ProviderService {

    private ProviderRepository providerRepository;

    public ProviderServiceImpl(JpaRepository<Provider, Long> repository) {
        super(repository);
    }

    @Autowired
    public void setProviderRepository(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @Override
    public void checkExistsProviderName(String providerName) throws DataExistsException {
        if(providerRepository.existsByProviderName(providerName)) {
            throw new DataExistsException("Provider name already exists");
        }
    }
}
