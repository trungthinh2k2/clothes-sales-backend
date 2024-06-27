package iuh.fit.salesappbackend.service.impl;

import iuh.fit.salesappbackend.dtos.requests.OrderDto;
import iuh.fit.salesappbackend.models.Order;
import iuh.fit.salesappbackend.service.interfaces.OrderService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, String> implements OrderService {
    public OrderServiceImpl(JpaRepository<Order, String> repository) {
        super(repository);
    }

    @Override
    public Order save(OrderDto orderDto) {
        return null;
    }
}
