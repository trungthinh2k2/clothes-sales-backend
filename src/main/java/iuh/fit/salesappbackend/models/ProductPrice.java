package iuh.fit.salesappbackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_prices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_price_id")
    private Long id;
    private Double discount;
    @Column(name = "discounted_price")
    private Double discountedPrice;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expried_date")
    private LocalDateTime expriedDate;
    private String note;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
