package iuh.fit.salesappbackend.controllers;

import iuh.fit.salesappbackend.dtos.requests.AddressDto;
import iuh.fit.salesappbackend.dtos.responses.ResponseError;
import iuh.fit.salesappbackend.dtos.responses.ResponseSuccess;
import iuh.fit.salesappbackend.mappers.AddressMapper;
import iuh.fit.salesappbackend.models.Address;
import iuh.fit.salesappbackend.service.interfaces.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;
    private final AddressMapper addressMapper;
    @PostMapping
    public ResponseSuccess<?> createAddress(@RequestBody @Valid AddressDto addressDto) {
        Address address = addressMapper.addressDto2Address(addressDto);
        return new ResponseSuccess<>(HttpStatus.OK.value(),
                "Address created successfully",
                addressService.save(address));
    }

    @PutMapping("/{id}")
    public ResponseSuccess<?> updateAddress(@PathVariable Long id, @RequestBody @Valid AddressDto addressDto) {
        Address address = addressMapper.addressDto2Address(addressDto);
        address.setId(id);
        return new ResponseSuccess<>(HttpStatus.OK.value(),
                "Address updated successfully",
                addressService.update(id, address));
    }

    @DeleteMapping("/{id}")
    public ResponseSuccess<?> deleteAddress(@PathVariable Long id) {
        addressService.deleteById(id);
        return new ResponseSuccess<>(
                HttpStatus.NO_CONTENT.value(),
                "Address deleted successfully with id: " + id);
    }

    @PatchMapping("/{id}")
    public ResponseSuccess<?> patchAddress(@PathVariable Long id, @RequestBody Map<String, ?> data) {
        return new ResponseSuccess<>(HttpStatus.OK.value(),
                "Address patched successfully",
                addressService.updatePatch(id, data));
    }
}
