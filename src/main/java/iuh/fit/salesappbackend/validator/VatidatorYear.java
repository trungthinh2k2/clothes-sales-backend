package iuh.fit.salesappbackend.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {ValidatorCommentDate.class})
public @interface VatidatorYear {
    String message() default "Invalid year";

    int min();
    int max();

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
