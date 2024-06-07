package iuh.fit.salesappbackend.validator;

<<<<<<< HEAD
import iuh.fit.salesappbackend.dtos.requests.comment.CommentDto;
=======
<<<<<<< HEAD
import iuh.fit.salesappbackend.dtos.requests.comment.CommentDto;
=======
>>>>>>> ae3eca8 (update service and upload image to S3, Cloudinary)
>>>>>>> fc45643 (update service and upload image to S3, Cloudinary)
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {ValidatorCommentDate.class})
public @interface ValidatorDate {
    String message() default "Invalid comment date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
