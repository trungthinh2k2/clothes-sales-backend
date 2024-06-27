package iuh.fit.salesappbackend.models;

import iuh.fit.salesappbackend.models.id_classes.OrderVoucherId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_voucher")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(OrderVoucherId.class)
public class OrderVoucher {
    @Id
    @ManyToOne
    @JoinColumn(name = "voucher_id", nullable = false)
    private Voucher voucher;
    @Id
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
