package iuh.fit.salesappbackend.controllers;

import iuh.fit.salesappbackend.dtos.requests.ProviderDto;
import iuh.fit.salesappbackend.dtos.responses.ResponseSuccess;
import iuh.fit.salesappbackend.exceptions.DataExistsException;
import iuh.fit.salesappbackend.mappers.ProviderMapper;
import iuh.fit.salesappbackend.models.Provider;
import iuh.fit.salesappbackend.service.interfaces.ProviderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/providers")
@RequiredArgsConstructor
public class ProviderController {

    private final ProviderService providerService;
    private final ProviderMapper providerMapper;

    @PostMapping
    public ResponseSuccess<?> createProvider(@RequestBody @Valid ProviderDto providerDto) throws DataExistsException {
        Provider provider = providerMapper.providerDto2Provider(providerDto);
        return new ResponseSuccess<>(
                HttpStatus.OK.value(),
                "Create provider successfully",
                providerService.save(provider));
    }

    @GetMapping
    public ResponseSuccess<?> getAllProviders() {
        return new ResponseSuccess<>(
                HttpStatus.OK.value(),
                "Get all providers successfully",
                providerService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseSuccess<?> deleteProvider(@PathVariable Long id) {
        providerService.deleteById(id);
        return new ResponseSuccess<>(
                HttpStatus.NO_CONTENT.value(),
                "Delete provider successfully", + id);
    }
}
