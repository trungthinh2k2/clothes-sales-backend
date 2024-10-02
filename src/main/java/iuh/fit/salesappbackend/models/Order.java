package iuh.fit.salesappbackend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import iuh.fit.salesappbackend.models.enums.DeliveryMethod;
import iuh.fit.salesappbackend.models.enums.OrderStatus;
import iuh.fit.salesappbackend.models.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order extends BaseModel{
    @Id
    @Column(name = "order_id")
    private String id;
    @Column(name = "order_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
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
    private Double originalAmount;        // tiền ban đầu
    @Column(name = "discounted_price")
    private Double discountedPrice;       // tiền được giảm
    @Column(name = "discounted_amount")
    private Double  discountedAmount;     // tiền sau khi giảm
    @Column(name = "delivery_amount")
    private Double deliveryAmount;
}
