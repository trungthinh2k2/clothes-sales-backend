package iuh.fit.salesappbackend.events;

import iuh.fit.salesappbackend.models.Product;
import iuh.fit.salesappbackend.service.interfaces.ProductRedisService;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductEvent {
    private final ProductRedisService productRedisService;

    @PostPersist
    public void postPersist(Product product) {
        productRedisService.clearCache();
    }

    @PostUpdate
    public void postUpdate(Product product) {
        productRedisService.clearCache();
    }

    @PostRemove
    public void postRemove(Product product) {
        productRedisService.clearCache();
    }
}
