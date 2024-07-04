package iuh.fit.salesappbackend.service.impl;

import iuh.fit.salesappbackend.models.ProductPrice;
import iuh.fit.salesappbackend.service.interfaces.ProductPriceService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductPriceServiceImpl extends  BaseServiceImpl<ProductPrice, Long>
        implements ProductPriceService {
    public ProductPriceServiceImpl(JpaRepository<ProductPrice, Long> repository) {
        super(repository, ProductPrice.class);
    }
}
