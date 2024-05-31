package iuh.fit.salesappbackend.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class ValidatorBirthYear implements ConstraintValidator<VatidatorYear, LocalDateTime> {

    private int min;
    private int max;

    @Override
    public void initialize(VatidatorYear constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
        int year = value.getYear();
        return year >= min && year <= max;
    }
}
