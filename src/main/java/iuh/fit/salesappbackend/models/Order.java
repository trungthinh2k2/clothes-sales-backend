package iuh.fit.salesappbackend.models;

import iuh.fit.salesappbackend.models.enums.DeliveryMethod;
import iuh.fit.salesappbackend.models.enums.OrderStatus;
import iuh.fit.salesappbackend.models.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseModel{
    @Id
    @Column(name = "order_id")
    private String id;
    @Column(name = "order_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;
    private String note;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "byer_name")
    private String byerName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_method")
    private DeliveryMethod deliveryMethod;;
    @Column(name = "original_amount")
    private Double originalAmount;
    @Column(name = "discounted_price")
    private Double discountedPrice;
    @Column(name = "discounted_amount")
    private Double  discountedAmount;
    @Column(name = "delivery_amount")
    private Double deliveryAmount;
}
