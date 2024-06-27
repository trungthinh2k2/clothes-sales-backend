package iuh.fit.salesappbackend.mappers;

import iuh.fit.salesappbackend.dtos.requests.ColorDto;
import iuh.fit.salesappbackend.exceptions.DataExistsException;
import iuh.fit.salesappbackend.models.Color;
import iuh.fit.salesappbackend.service.interfaces.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ColorMapper {
    private final ColorService colorService;
    public Color colorDto2Color(ColorDto colorDto) throws DataExistsException {
        colorService.checkExistsColorName(colorDto.getColorName());
        Color color = new Color();
        color.setColorName(colorDto.getColorName());
        return color;
    }
}
