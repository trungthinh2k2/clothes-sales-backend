package iuh.fit.salesappbackend.models;

import jakarta.persistence.*;
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> fc45643 (update service and upload image to S3, Cloudinary)
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
<<<<<<< HEAD
=======
=======
import lombok.*;
>>>>>>> ae3eca8 (update service and upload image to S3, Cloudinary)
>>>>>>> fc45643 (update service and upload image to S3, Cloudinary)

@Entity
@Table(name = "product_details")
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
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_detail_id")
    private Long id;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    @ManyToOne
    @JoinColumn(name = "size_id", nullable = false)
    private Size size;
    @ManyToOne
    @JoinColumn(name = "color_id", nullable = false)
    private Color color;
}
