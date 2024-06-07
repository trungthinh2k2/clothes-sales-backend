package iuh.fit.salesappbackend.models;

import iuh.fit.salesappbackend.models.id_classes.OrderDetailId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.*;
@Entity
@Table(name = "order_datails")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(OrderDetailId.class)
public class OrderDetail {
    @Id
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    @Id
    @ManyToOne
    @JoinColumn(name = "product_detail_id", nullable = false)
    private ProductDetail productDetail;
    private Integer quantity;
    private Double amount;
}
