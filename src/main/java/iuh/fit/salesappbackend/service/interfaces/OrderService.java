package iuh.fit.salesappbackend.service.interfaces;

import iuh.fit.salesappbackend.dtos.requests.OrderDto;
import iuh.fit.salesappbackend.models.Order;

public interface OrderService extends BaseService<Order, String> {
    Order save(OrderDto orderDto);
}
