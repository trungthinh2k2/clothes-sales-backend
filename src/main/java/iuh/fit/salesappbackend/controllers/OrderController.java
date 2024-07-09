package iuh.fit.salesappbackend.controllers;

import iuh.fit.salesappbackend.dtos.requests.OrderDto;
import iuh.fit.salesappbackend.dtos.responses.ResponseSuccess;
import iuh.fit.salesappbackend.exceptions.DataNotFoundException;
import iuh.fit.salesappbackend.service.interfaces.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseSuccess<?> createOrder(@RequestBody @Valid OrderDto orderDto)
            throws DataNotFoundException {
        return new ResponseSuccess<>(
                HttpStatus.OK.value(),
                "create order successfully",
                orderService.save(orderDto)
        );
    }
 }
