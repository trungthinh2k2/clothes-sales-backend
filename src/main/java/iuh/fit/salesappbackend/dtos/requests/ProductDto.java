package iuh.fit.salesappbackend.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@Builder
public class ProductDto{
    @NotBlank(message = "Product name must be not blank")
    private String productName;
    @NotNull(message = "Price must be not null")
    private Double price;
    @NotNull(message = "Description must be not blank")
    private String description;
    private Integer thumbnail;
    @NotNull(message = "Category must be not null")
    private Long categoryId;
    @NotNull(message = "Provider must be not null")
    private Long providerId;
    private List<MultipartFile> images;
}