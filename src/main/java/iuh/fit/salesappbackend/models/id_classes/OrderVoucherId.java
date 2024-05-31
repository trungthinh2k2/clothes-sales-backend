package iuh.fit.salesappbackend.models.id_classes;

import iuh.fit.salesappbackend.models.Order;
import iuh.fit.salesappbackend.models.Voucher;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode
public class OrderVoucherId implements Serializable {
    private Voucher voucher;
    private Order order;
}

