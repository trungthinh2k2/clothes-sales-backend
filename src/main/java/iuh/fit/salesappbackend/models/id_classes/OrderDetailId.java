package iuh.fit.salesappbackend.models.id_classes;

import iuh.fit.salesappbackend.models.Order;
import iuh.fit.salesappbackend.models.ProductDetail;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class OrderDetailId implements Serializable {
    private Order order;
    private ProductDetail productDetail;
}
