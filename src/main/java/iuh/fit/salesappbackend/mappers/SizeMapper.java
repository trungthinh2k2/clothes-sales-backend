package iuh.fit.salesappbackend.mappers;

import iuh.fit.salesappbackend.dtos.requests.SizeDto;
import iuh.fit.salesappbackend.exceptions.DataExistsException;
import iuh.fit.salesappbackend.exceptions.NullDataException;
import iuh.fit.salesappbackend.models.Size;
import iuh.fit.salesappbackend.service.interfaces.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SizeMapper {
    private final SizeService sizeService;
    public Size sizeDto2Size(SizeDto sizeDto) throws NullDataException, DataExistsException {
        if(sizeDto.getNumberSize() == null && sizeDto.getTextSize() == null) {
            throw new NullDataException("text or num size must be not null");
        }
        if(sizeDto.getNumberSize() != null) {
            sizeService.checkExistsNumberSize(sizeDto.getNumberSize());
        }
        if(sizeDto.getTextSize() != null) {
            sizeService.checkExistsTextSize(sizeDto.getTextSize());
        }
        Size size = new Size();
        size.setNumberSize(sizeDto.getNumberSize());
        size.setTextSize(sizeDto.getTextSize());
        size.setSizeType(sizeDto.getSizeType());
        return size;
    }
}
