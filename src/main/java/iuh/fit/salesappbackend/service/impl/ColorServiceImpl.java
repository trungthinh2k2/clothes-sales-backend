package iuh.fit.salesappbackend.service.impl;

import iuh.fit.salesappbackend.exceptions.DataExistsException;
import iuh.fit.salesappbackend.models.Color;
import iuh.fit.salesappbackend.repositories.ColorRepository;
import iuh.fit.salesappbackend.service.interfaces.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ColorServiceImpl extends BaseServiceImpl<Color, Long> implements ColorService {
    private ColorRepository colorRepository;

    public ColorServiceImpl(JpaRepository<Color, Long> repository, ColorRepository colorRepository) {
        super(repository, Color.class);
    }

    @Autowired
    public void setColorRepository(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    @Override
    public void checkExistsColorName(String colorName) throws DataExistsException {
        if (colorRepository.existsByColorName(colorName)) {
            throw new DataExistsException("Color name already exists");
        }
    }
}
