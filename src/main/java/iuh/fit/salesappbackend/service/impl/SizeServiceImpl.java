package iuh.fit.salesappbackend.service.impl;

import iuh.fit.salesappbackend.exceptions.DataExistsException;
import iuh.fit.salesappbackend.models.Size;
import iuh.fit.salesappbackend.repositories.SizeRepository;
import iuh.fit.salesappbackend.service.interfaces.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class SizeServiceImpl extends BaseServiceImpl<Size, Long> implements SizeService {

    private SizeRepository sizeRepository;

    public SizeServiceImpl(JpaRepository<Size, Long> repository) {
        super(repository, Size.class);
    }

    @Autowired
    public void setSizeRepository(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    @Override
    public void checkExistsTextSize(String textSize) throws DataExistsException {
        if (sizeRepository.existsByTextSize(textSize)) {
            throw new DataExistsException("Text size already exists");
        }
    }

    @Override
    public void checkExistsNumberSize(Integer numberSize) throws DataExistsException {
        if (sizeRepository.existsByNumberSize(numberSize)) {
            throw new DataExistsException("Number size already exists");
        }
    }
}
