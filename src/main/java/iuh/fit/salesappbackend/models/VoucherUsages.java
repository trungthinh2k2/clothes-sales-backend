package iuh.fit.salesappbackend.models;

import iuh.fit.salesappbackend.models.id_classes.UserVoucherId;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "voucher_usages")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(UserVoucherId.class)
public class VoucherUsages {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Id
    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;
    @Column(name = "usages_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime usagesDate;
}
