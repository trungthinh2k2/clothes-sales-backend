package iuh.fit.salesappbackend.models.id_classes;

import iuh.fit.salesappbackend.models.Order;
import iuh.fit.salesappbackend.models.ProductDetail;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
import lombok.NoArgsConstructor;
>>>>>>> ae3eca8 (update service and upload image to S3, Cloudinary)
>>>>>>> fc45643 (update service and upload image to S3, Cloudinary)

import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
@NoArgsConstructor
>>>>>>> ae3eca8 (update service and upload image to S3, Cloudinary)
>>>>>>> fc45643 (update service and upload image to S3, Cloudinary)
public class OrderDetailId implements Serializable {
    private Order order;
    private ProductDetail productDetail;
}
