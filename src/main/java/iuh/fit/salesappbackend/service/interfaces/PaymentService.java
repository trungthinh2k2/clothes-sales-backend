package iuh.fit.salesappbackend.service.interfaces;

import jakarta.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;

public interface PaymentService {
    String paymentUrl(HttpServletRequest req) throws UnsupportedEncodingException;
}
