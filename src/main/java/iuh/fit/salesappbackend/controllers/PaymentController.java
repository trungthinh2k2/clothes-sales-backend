package iuh.fit.salesappbackend.controllers;

import iuh.fit.salesappbackend.dtos.responses.Response;
import iuh.fit.salesappbackend.dtos.responses.ResponseSuccess;
import iuh.fit.salesappbackend.service.interfaces.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/vnp")
    public Response payment(HttpServletRequest req) throws Exception {
        return new ResponseSuccess<>(
                HttpStatus.OK.value(),
                "Payment successfully",
                paymentService.paymentUrl(req)
        );
    }
}
