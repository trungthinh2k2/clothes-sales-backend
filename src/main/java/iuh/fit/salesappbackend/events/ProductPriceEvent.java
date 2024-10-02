package iuh.fit.salesappbackend.events;

import iuh.fit.salesappbackend.models.ProductPrice;
import iuh.fit.salesappbackend.service.interfaces.ProductRedisService;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductPriceEvent {
    private final ProductRedisService productRedisService;

    @PostPersist
    public void postPersist(ProductPrice product) {
        productRedisService.clearCache();
    }

    @PostUpdate
    public void postUpdate(ProductPrice product) {
        productRedisService.clearCache();
    }

    @PostRemove
    public void postRemove(ProductPrice product) {
        productRedisService.clearCache();
    }
}
