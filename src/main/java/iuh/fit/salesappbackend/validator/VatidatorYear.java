package iuh.fit.salesappbackend.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD})
@Retention(RUNTIME)
<<<<<<< HEAD
@Constraint(validatedBy = {ValidatorCommentDate.class})
=======
<<<<<<< HEAD
@Constraint(validatedBy = {ValidatorCommentDate.class})
=======
@Constraint(validatedBy = {ValidatorBirthYear.class})
>>>>>>> ae3eca8 (update service and upload image to S3, Cloudinary)
>>>>>>> fc45643 (update service and upload image to S3, Cloudinary)
public @interface VatidatorYear {
    String message() default "Invalid year";

    int min();
    int max();

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
