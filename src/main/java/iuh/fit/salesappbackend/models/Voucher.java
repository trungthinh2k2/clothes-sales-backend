package iuh.fit.salesappbackend.models;

import iuh.fit.salesappbackend.models.enums.Scope;
import iuh.fit.salesappbackend.models.enums.VoucherType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "voucher")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voucher_id")
    private Long id;
    @Column(name = "max_price")
    private Double maxPrice;
    @Column(name = "min_amount")
    private Double minAmount;
    private Double discount;;
    @Enumerated(EnumType.STRING)
    @Column(name = "voucher_type")
    private VoucherType voucherType;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expried_date")
    private LocalDateTime expriedDate;
    @Enumerated(EnumType.STRING)
    private Scope scope;
    private Integer quantity;
}
