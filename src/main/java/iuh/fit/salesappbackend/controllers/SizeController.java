package iuh.fit.salesappbackend.controllers;

import iuh.fit.salesappbackend.dtos.requests.SizeDto;
import iuh.fit.salesappbackend.dtos.responses.ResponseSuccess;
import iuh.fit.salesappbackend.exceptions.DataExistsException;
import iuh.fit.salesappbackend.exceptions.NullDataException;
import iuh.fit.salesappbackend.mappers.SizeMapper;
import iuh.fit.salesappbackend.models.Size;
import iuh.fit.salesappbackend.service.interfaces.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sizes")
@RequiredArgsConstructor
public class SizeController {

    private final SizeService sizeService;
    private final SizeMapper sizeMapper;

    @PostMapping
    public ResponseSuccess<?> createSize(@RequestBody SizeDto sizeDto)
            throws DataExistsException, NullDataException {
        Size size = sizeMapper.sizeDto2Size(sizeDto);
        return new ResponseSuccess<>(
                HttpStatus.OK.value(),
                "Create size successfully",
                sizeService.save(size)
        );
    }

    @GetMapping
    public ResponseSuccess<?> getAllSizes() {
        return new ResponseSuccess<>(
                HttpStatus.OK.value(),
                "Get all sizes successfully",
                sizeService.findAll()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseSuccess<?> deleteUser(@PathVariable Long id) {
        sizeService.deleteById(id);
        return new ResponseSuccess<>(
                HttpStatus.NO_CONTENT.value(),
                "Size deleted successfully with id: " + id
        );
    }

    @GetMapping("/page-size")
    public ResponseSuccess<?> getAllSizeWithPage(@RequestParam(defaultValue = "1") int pageNo,
                                          @RequestParam(defaultValue = "5") int pageSize,
                                          @RequestParam(required = false) String[] sort,
                                          @RequestParam(required = false, defaultValue = "") String[] search) {
        return new ResponseSuccess<>(
                HttpStatus.OK.value(),
                "Get size successfully with page",
                sizeService.getPageData(pageNo, pageSize, search, sort)
        );
    }
}
