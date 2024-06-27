package iuh.fit.salesappbackend.controllers;

import iuh.fit.salesappbackend.dtos.requests.ProductPriceDto;
import iuh.fit.salesappbackend.dtos.responses.ResponseSuccess;
import iuh.fit.salesappbackend.exceptions.DataNotFoundException;
import iuh.fit.salesappbackend.mappers.ProductPriceMapper;
import iuh.fit.salesappbackend.models.ProductPrice;
import iuh.fit.salesappbackend.service.interfaces.ProductPriceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/productPrices")
@RequiredArgsConstructor
public class ProductPriceController {

    private final ProductPriceService productPriceService;
    private final ProductPriceMapper productPriceMapper;

    @PostMapping
    public ResponseSuccess<?> createProductPrice(@RequestBody @Valid ProductPriceDto productPriceDto)
            throws DataNotFoundException {

        ProductPrice productPrice = productPriceMapper.productPriceDto2ProductPrice(productPriceDto);
        return new ResponseSuccess<>(
                HttpStatus.OK.value(),
                "Product price  created successfully",
                productPriceService.save(productPrice)
        );
    }


}
