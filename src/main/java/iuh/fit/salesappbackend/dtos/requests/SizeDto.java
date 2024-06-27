package iuh.fit.salesappbackend.dtos.requests;

import iuh.fit.salesappbackend.models.enums.SizeType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SizeDto {
    private SizeType sizeType;
    private Integer numberSize;
    private String textSize;
}
