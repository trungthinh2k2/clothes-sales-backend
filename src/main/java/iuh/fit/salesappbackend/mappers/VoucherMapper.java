package iuh.fit.salesappbackend.mappers;

import iuh.fit.salesappbackend.dtos.requests.VoucherDto;
import iuh.fit.salesappbackend.models.Voucher;
import org.springframework.stereotype.Component;

@Component
public class VoucherMapper {
    public Voucher voucherDTO2Voucher(VoucherDto voucherDto) {
        return Voucher.builder()
                .maxPrice(voucherDto.getMaxPrice())
                .minAmount(voucherDto.getMinAmount())
                .discount(voucherDto.getDiscount())
                .voucherType(voucherDto.getVoucherType())
                .expriedDate(voucherDto.getExpriedDate())
                .scope(voucherDto.getScope())
                .quantity(voucherDto.getQuantity())
                .build();
    }
}
