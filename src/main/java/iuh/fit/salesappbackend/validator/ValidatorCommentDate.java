package iuh.fit.salesappbackend.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class ValidatorCommentDate implements ConstraintValidator<ValidatorDate, LocalDateTime> {
    @Override
    public void initialize(ValidatorDate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Không kiểm tra nếu trường là null
        }
        return !value.isAfter(LocalDateTime.now());
    }
}
