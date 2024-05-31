package iuh.fit.salesappbackend.models.id_classes;

import iuh.fit.salesappbackend.models.User;
import iuh.fit.salesappbackend.models.Voucher;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode
public class UserVoucherId implements Serializable {
    private User user;
    private Voucher voucher;
}
