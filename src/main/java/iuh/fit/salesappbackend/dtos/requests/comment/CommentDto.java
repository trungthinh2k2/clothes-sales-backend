package iuh.fit.salesappbackend.dtos.requests.comment;

import iuh.fit.salesappbackend.validator.ValidatorDate;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
public class CommentDto {
    @ValidatorDate
    LocalDateTime createdAt;
    @ValidatorDate
    LocalDateTime updatedAt;
    @ValidatorDate
    LocalDateTime commentDate;
    String textContent;
    @Min(value = 1, message = "Rating must be greater than 1")
    @Max(value = 5, message = "Rating must be less than 5")
    Integer rating;
}