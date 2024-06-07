package iuh.fit.salesappbackend.mappers;

import iuh.fit.salesappbackend.dtos.requests.UserVoucherDto;
import iuh.fit.salesappbackend.models.User;
import iuh.fit.salesappbackend.models.UserVoucher;
import iuh.fit.salesappbackend.models.Voucher;
import iuh.fit.salesappbackend.service.interfaces.UserService;
import iuh.fit.salesappbackend.service.interfaces.VoucherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserVoucherMapper {

    private final UserService userService;
    private final VoucherService voucherService;

    public UserVoucher userVoucherDTO2UserVoucher(UserVoucherDto userVoucherDto) {
        UserVoucher userVoucher = new UserVoucher();
        userVoucher.setIsUsed(userVoucherDto.getIsUsed());

        User user = userService.findById(userVoucherDto.getUserId()).orElseThrow();
        Voucher voucher = voucherService.findById(userVoucherDto.getVoucherId()).orElseThrow();
        userVoucher.setUser(user);
        userVoucher.setVoucher(voucher);

        return userVoucher;
    }
}
