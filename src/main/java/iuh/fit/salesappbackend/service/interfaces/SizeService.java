package iuh.fit.salesappbackend.service.interfaces;

import iuh.fit.salesappbackend.exceptions.DataExistsException;
import iuh.fit.salesappbackend.models.Size;

public interface SizeService extends BaseService<Size, Long>{
    void checkExistsTextSize(String textSize) throws DataExistsException;
    void checkExistsNumberSize(Integer numberSize) throws DataExistsException;
}
