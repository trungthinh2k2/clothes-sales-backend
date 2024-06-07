package iuh.fit.salesappbackend.controllers;

import iuh.fit.salesappbackend.dtos.requests.UserVoucherDto;
import iuh.fit.salesappbackend.dtos.responses.ResponseSuccess;
import iuh.fit.salesappbackend.mappers.UserVoucherMapper;
import iuh.fit.salesappbackend.models.UserVoucher;
import iuh.fit.salesappbackend.models.id_classes.UserVoucherId;
import iuh.fit.salesappbackend.service.interfaces.UserVoucherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/userVoucher")
@RequiredArgsConstructor
public class UserVoucherController {

    private final UserVoucherService userVoucherService;
    private final UserVoucherMapper userVoucherMapper;

    @PostMapping
    public ResponseSuccess<?> createUserVoucher(@RequestBody @Valid UserVoucherDto userVoucherDto) {
        UserVoucher userVoucher = userVoucherMapper.userVoucherDTO2UserVoucher(userVoucherDto);
        return new ResponseSuccess<>(
                HttpStatus.OK.value(),
                "Create user voucher successfully",
                userVoucherService.save(userVoucher)
        );
    }

    @PutMapping()
    public ResponseSuccess<?> updateUserVoucher(@RequestBody @Valid UserVoucherDto userVoucherDto) {
        UserVoucher userVoucher = userVoucherMapper.userVoucherDTO2UserVoucher(userVoucherDto);
        UserVoucherId userVoucherId = new UserVoucherId(userVoucher.getUser(), userVoucher.getVoucher());
        return new ResponseSuccess<>(
                HttpStatus.OK.value(),
                "Update user voucher successfully",
                userVoucherService.update(userVoucherId,userVoucher)
        );
    }


}
