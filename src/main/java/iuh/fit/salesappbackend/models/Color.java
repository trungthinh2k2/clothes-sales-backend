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
@Table(name = "colors")
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
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "color_id")
    private Long id;
    @Column(name = "color_name")
    private String colorName;
}
