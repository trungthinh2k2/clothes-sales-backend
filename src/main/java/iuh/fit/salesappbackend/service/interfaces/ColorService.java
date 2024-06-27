package iuh.fit.salesappbackend.service.interfaces;

import iuh.fit.salesappbackend.exceptions.DataExistsException;
import iuh.fit.salesappbackend.models.Color;

public interface ColorService extends BaseService<Color, Long>{
    void checkExistsColorName(String colorName) throws DataExistsException;

}
