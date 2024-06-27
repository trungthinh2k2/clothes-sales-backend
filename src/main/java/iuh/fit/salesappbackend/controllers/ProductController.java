package iuh.fit.salesappbackend.controllers;

import iuh.fit.salesappbackend.dtos.requests.ProductDto;
import iuh.fit.salesappbackend.dtos.responses.ResponseSuccess;
import iuh.fit.salesappbackend.exceptions.DataExistsException;
import iuh.fit.salesappbackend.exceptions.DataNotFoundException;
import iuh.fit.salesappbackend.mappers.ProductMapper;
import iuh.fit.salesappbackend.service.interfaces.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping
    public ResponseSuccess<?> createProduct(@ModelAttribute @Valid ProductDto productDto) throws DataNotFoundException, DataExistsException {
        return new ResponseSuccess<>(
                HttpStatus.OK.value(),
                "Product created successfully",
                productService.save(productDto)
        );
    }

    @GetMapping
    public ResponseSuccess<?> getAllProducts() {
        return new ResponseSuccess<>(
                HttpStatus.OK.value(),
                "Get all products successfully",
                productService.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseSuccess<?> getProductById(@PathVariable Long id) throws DataNotFoundException {
        return new ResponseSuccess<>(
                HttpStatus.OK.value(),
                "Get product by id successfully",
                productService.findProductById(id)
        );
    }
}
