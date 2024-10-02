package iuh.fit.salesappbackend.controllers;

import iuh.fit.salesappbackend.dtos.requests.ColorDto;
import iuh.fit.salesappbackend.dtos.responses.ResponseSuccess;
import iuh.fit.salesappbackend.exceptions.DataExistsException;
import iuh.fit.salesappbackend.mappers.ColorMapper;
import iuh.fit.salesappbackend.models.Color;
import iuh.fit.salesappbackend.service.interfaces.ColorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/colors")
@RequiredArgsConstructor
public class ColorController {
    private final ColorService colorService;
    private final ColorMapper colorMapper;
    @PostMapping
    public ResponseSuccess<?> createColor(@RequestBody @Valid ColorDto categoryDto) throws DataExistsException {
        Color color = colorMapper.colorDto2Color(categoryDto);
        return new ResponseSuccess<>(HttpStatus.OK.value(),
                "Color created successfully",
                colorService.save(color));
    }

    @GetMapping
    public ResponseSuccess<?> getAllColors() {
        return new ResponseSuccess<>(
                HttpStatus.OK.value(),
                "Get all colors successfully",
                colorService.findAll()
        );
    }

    @GetMapping("/page-color")
    public ResponseSuccess<?> getAllColor(@RequestParam(defaultValue = "1") int pageNo,
                                          @RequestParam(defaultValue = "5") int pageSize,
                                          @RequestParam(required = false) String[] sort,
                                          @RequestParam(required = false, defaultValue = "") String[] search) {
        return new ResponseSuccess<>(
                HttpStatus.OK.value(),
                "Get color by id successfully",
                colorService.getPageData(pageNo, pageSize, search, sort)
        );
    }
}
