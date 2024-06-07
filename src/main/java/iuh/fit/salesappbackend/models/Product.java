package iuh.fit.salesappbackend.models;

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> fc45643 (update service and upload image to S3, Cloudinary)
import iuh.fit.salesappbackend.models.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
<<<<<<< HEAD
=======
=======
import iuh.fit.salesappbackend.models.enums.Status;
import jakarta.persistence.*;
import lombok.*;
>>>>>>> ae3eca8 (update service and upload image to S3, Cloudinary)
>>>>>>> fc45643 (update service and upload image to S3, Cloudinary)

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
@Builder
>>>>>>> ae3eca8 (update service and upload image to S3, Cloudinary)
>>>>>>> fc45643 (update service and upload image to S3, Cloudinary)
public class Product extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    @Column(name = "product_name")
    private String productName;
    @Column(columnDefinition = "DECIMAL(10,2)")
    private Double price;
    @Column(name = "avg_rating",columnDefinition = "DECIMAL(10,1)")
    private Float avgRating;;
    private String description;
    private String thumbnail;
    @Column(name = "number_of_rating")
    private Integer numberOfRating;
    @Enumerated(EnumType.STRING)
    @Column(name = "product_status")
<<<<<<< HEAD
    private ProductStatus productStatus;
=======
<<<<<<< HEAD
    private ProductStatus productStatus;
=======
    private Status productStatus;
>>>>>>> ae3eca8 (update service and upload image to S3, Cloudinary)
>>>>>>> fc45643 (update service and upload image to S3, Cloudinary)
    @Column(name = "total_quantity")
    private Integer totalQuantity;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;
}
