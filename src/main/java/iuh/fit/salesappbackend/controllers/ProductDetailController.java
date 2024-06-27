package iuh.fit.salesappbackend.controllers;

import iuh.fit.salesappbackend.dtos.requests.ProductDetailDto;
import iuh.fit.salesappbackend.dtos.responses.ResponseSuccess;
import iuh.fit.salesappbackend.exceptions.DataNotFoundException;
import iuh.fit.salesappbackend.mappers.ProductDetailMapper;
import iuh.fit.salesappbackend.models.ProductDetail;
import iuh.fit.salesappbackend.service.interfaces.ProductDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/productDetails")
@RequiredArgsConstructor
public class ProductDetailController {

    private final ProductDetailService productDetailService;
    private final ProductDetailMapper productDetailMapper;

    @PostMapping
    public ResponseSuccess<?> createProductDetail(@RequestBody @Valid ProductDetailDto productDetailDto)
            throws DataNotFoundException {
        ProductDetail productDetail = productDetailMapper.productDetailDto2ProductDetail(productDetailDto);
        return new ResponseSuccess<>(
                HttpStatus.OK.value(),
                "Product detail created successfully",
                productDetailService.save(productDetail));
    }

    @PatchMapping("/{id}")
    public ResponseSuccess<?> updatePatchProductDetail(@PathVariable Long id,@RequestBody Map<String, ?> data) {
        return new ResponseSuccess<>(
                HttpStatus.OK.value(),
                "Product detail updated successfully",
                productDetailService.updatePatch(id, data)
        );
    }
}
