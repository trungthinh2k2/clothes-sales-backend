package iuh.fit.salesappbackend.models;

import iuh.fit.salesappbackend.models.id_classes.UserVoucherId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_voucher")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(UserVoucherId.class)
public class UserVoucher {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Id
    @ManyToOne
    @JoinColumn(name = "voucher_id", nullable = false)
    private Voucher voucher;
    @Column(name = "is_used")
    private Boolean isUsed;
}
