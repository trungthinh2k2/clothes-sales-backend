package iuh.fit.salesappbackend.models;

import iuh.fit.salesappbackend.models.enums.SizeType;
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
@Table(name = "sizes")
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
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "size_id")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "size_type")
    private SizeType sizeType;
    @Column(name = "number_size")
    private Integer numberSize;
    @Column(name = "text_size")
    private String textSize;
}
