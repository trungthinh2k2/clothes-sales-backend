package iuh.fit.salesappbackend.service.interfaces;

import iuh.fit.salesappbackend.exceptions.DataExistsException;
import iuh.fit.salesappbackend.models.Provider;

public interface ProviderService extends BaseService<Provider, Long> {
    void checkExistsProviderName(String providerName) throws DataExistsException;
}
