package iuh.fit.salesappbackend.controllers;

import iuh.fit.salesappbackend.dtos.requests.VoucherDto;
import iuh.fit.salesappbackend.dtos.responses.ResponseSuccess;
import iuh.fit.salesappbackend.mappers.VoucherMapper;
import iuh.fit.salesappbackend.models.Voucher;
import iuh.fit.salesappbackend.service.interfaces.VoucherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vouchers")
@RequiredArgsConstructor
public class VoucherController {

    private final VoucherService voucherService;
    private final VoucherMapper voucherMapper;

    @PostMapping
    public ResponseSuccess<?> createVoucher(@RequestBody @Valid VoucherDto voucherDto) {
        Voucher voucher = voucherMapper.voucherDTO2Voucher(voucherDto);
        return new ResponseSuccess<>(
                HttpStatus.OK.value(),
                "Create voucher successfully",
                voucherService.save(voucher)
        );
    }

}
