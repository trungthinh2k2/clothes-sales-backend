package iuh.fit.salesappbackend.mappers;

import iuh.fit.salesappbackend.dtos.requests.UserVoucherDto;
import iuh.fit.salesappbackend.exceptions.DataNotFoundException;
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

    public UserVoucher userVoucherDTO2UserVoucher(UserVoucherDto userVoucherDto) throws DataNotFoundException {
        User user = userService.findById(userVoucherDto.getUserId())
                .orElseThrow(() -> new DataNotFoundException("user not found"));
        Voucher voucher = voucherService.findById(userVoucherDto.getVoucherId())
                .orElseThrow(() -> new DataNotFoundException("voucher not found"));
        UserVoucher userVoucher = new UserVoucher();
        userVoucher.setIsUsed(false);
        userVoucher.setVoucher(voucher);
        userVoucher.setUser(user);
        return userVoucher;
    }
}
